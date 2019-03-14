package com.edwin.security.app;

import com.edwin.security.app.social.openid.OpenIdAuthenticationSecurityConfig;
import com.edwin.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.edwin.security.core.properties.SecurityConstants;
import com.edwin.security.core.properties.SecurityProperties;
import com.edwin.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Created by wenpuzhao on 2019/3/11.
 */

@Configuration
@EnableResourceServer
public class MyResourcesServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    // 由下面的  .apply(smsCodeAuthenticationSecurityConfigs)方法添加这个配置
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfigs;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 之前配置的security的basic的 不能去掉哦。否则授权码模式又不能使用了
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler);
        http
                // 视频中说验证码的功能还有一点问题，先不用
                .apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfigs)
                .and()
                .apply(openIdAuthenticationSecurityConfig)
                .and()
                // 对请求授权配置：注意方法名的含义，能联想到一些
                .authorizeRequests()
                // 放行这个路径
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPENID,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*", // 图形验证码接口
                        securityProperties.getBrowser().getSignUpUrl(),  // 注册页面
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                        "/user/regist","/social/signUp",
                        "/error",
                        "/connect/*",
                        "/auth/*",
                        "/signin","/index.html","/weapp/**","/**"
                )
                .permitAll()
                .anyRequest()
                // 对任意请求都必须是已认证才能访问
                .authenticated()
                .and()
                .csrf()
                .disable();
    }
}
