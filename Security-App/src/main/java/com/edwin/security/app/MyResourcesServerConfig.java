package com.edwin.security.app;

import com.edwin.security.app.social.openid.OpenIdAuthenticationSecurityConfig;
import com.edwin.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.edwin.security.core.authorize.AuthorizeConfigManager;
import com.edwin.security.core.properties.SecurityConstants;
import com.edwin.security.core.properties.SecurityProperties;
import com.edwin.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
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

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

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
                .csrf()
                .disable();
        // 注入进来，然后把调用下配置对象即可
        // 可以看到上面的配置都没有了http.authorizeRequests()的配置
        // 全部由具体的去实现配置了
        // app项目中的安全配置改动其实和这里一样
        authorizeConfigManager.config(http.authorizeRequests());
    }

    // 之后引入的bean是为了解决no bean resolver registered的问题
    // https://github.com/spring-projects/spring-security-oauth/issues/730#issuecomment-219480394

    @Autowired
    private OAuth2WebSecurityExpressionHandler expressionHandler;
    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.expressionHandler(expressionHandler);
    }
}
