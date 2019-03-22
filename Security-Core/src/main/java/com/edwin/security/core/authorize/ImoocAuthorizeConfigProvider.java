package com.edwin.security.core.authorize;

import com.edwin.security.core.properties.SecurityConstants;
import com.edwin.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * Created by wenpuzhao on 2019/3/21.
 */
@Component
@Order(Integer.MIN_VALUE)
public class ImoocAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(
                SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
//                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPEN_ID,
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                securityProperties.getBrowser().getLoginPage(),
                securityProperties.getBrowser().getSignUpUrl(),
                securityProperties.getBrowser().getSession().getSessionInvalidUrl() + ".json",
                securityProperties.getBrowser().getSession().getSessionInvalidUrl() + ".html"
        ).permitAll();
//        // 退出成功处理，没有默认值，所以需要判定下
//        String signOutUrl = securityProperties.getBrowser().getSignOutUrl();
//        if (signOutUrl != null) {
//            config.antMatchers(signOutUrl).permitAll();
//        }
    }
}
