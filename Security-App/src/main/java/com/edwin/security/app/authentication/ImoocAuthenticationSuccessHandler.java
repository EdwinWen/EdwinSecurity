package com.edwin.security.app.authentication;

import com.edwin.security.core.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wenpuzhao on 2019/3/11.
 */

@Component("imoocAuthenticationSuccessHandler")
public class ImoocAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private RequestCache requestCache=new HttpSessionRequestCache();// 从cache中拿到跳转的页面信息
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;


    /**
     * 授权服务器：自动配置的
     * @see
     */
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


        /**
         * @see BasicAuthenticationFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
         *  */
        String header = request.getHeader("Authorization");
        logger.info("公众号ID："+request.getHeader("gzhId"));
        logger.info("DeviceId:"+request.getHeader("deviceId"));

        if (header == null || !header.startsWith("Basic ")) {
            // 不被认可的客户端异常
            throw new UnapprovedClientAuthenticationException("没有Authorization请求头");
        }

        // 解析请Authorization 获取client信息
        // client-id: myid
        // client-secret: myid
        String[] tokens = extractAndDecodeHeader(header, request);
        assert tokens.length == 2;
        String clientId = tokens[0];
        String clientSecret = tokens[1];
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        // 判定提交的是否与查询的匹配

        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
        } else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
        }
        /**  @see DefaultOAuth2RequestFactory#createTokenRequest(java.util.Map, org.springframework.security.oauth2.provider.ClientDetails)
         * requestParameters,不同的授权模式有不同的参数，这里自定义的模式，没有参数
         * String clientId,
         * Collection<String> scope, 给自己的前段使用，默认用所有的即可
         * String grantType 自定义
         *
         * 在这里我就有一个疑问了：这个token应该代表的是不同的用户，这里使用我们配置的同一个client？那么获取到的不就是相同的token？
         * 难道说是根据用户名和密码创建的？以后明白了再来填坑

         * 后补填坑：org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter 跟源码这里
         * 能看到，之前在发送 token 的时候 其实是和 用户信息（针对当前这个场景流程来说）关联上的，并且放入了 tokenService 中
         * 验证的时候从 tokenService 中获取出来的
         * */

        // 如果登录成功，并且判断出来是从公众号进来的，则把 DeviceID也就是微信ID和公众号ID保存起来到数据库
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(request.getHeader("deviceId"));
        // 判断不是手机号，则进行数据库保存
//        if( !isNum.matches() ){
//            MySecurityUser user= (MySecurityUser)authentication.getPrincipal();
//            user.administrators.setWeixin_id(request.getHeader("deviceId"));
//            user.administrators.setPublic_id(request.getHeader("gzhId"));
//            customerService.updateAdministarators(user.administrators);
//        }
        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_SORTED_MAP, clientId, clientDetails.getScope(), "costom");
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

        /**
         * @see org.springframework.security.oauth2.provider.token.AbstractTokenGranter#getOAuth2Authentication(org.springframework.security.oauth2.provider.ClientDetails, org.springframework.security.oauth2.provider.TokenRequest)
         * */
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);

        // 在后面测试的时候居然抛出了一个 事物异常 Could not open JDBC Connection for transaction; nested exception is ja
        // 我的数据库密码写错了，这个方法上加了一个@Transactional注解
        OAuth2AccessToken accessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        Map<String, Object> userMap = new HashedMap();
        userMap.put("accessToken",accessToken);
        userMap.put("userInfo", authentication.getPrincipal());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(userMap));



    }

    /**
     * Decodes the header into a username and password.
     * @throws BadCredentialsException if the Basic header is not present or is not valid
     *                                 Base64
     */
    private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {

        byte[] base64Token = header.substring(6).getBytes("UTF-8");
        byte[] decoded;
        try {
            decoded = Base64.getDecoder().decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException(
                    "Failed to decode basic authentication token");
        }

        String token = new String(decoded, "UTF-8");

        int delim = token.indexOf(":");

        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        return new String[]{token.substring(0, delim), token.substring(delim + 1)};
    }
}