package com.edwin.security;

import com.edwin.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * Created by wenpuzhao on 2019/3/21.
 */
@Component
@Order(Integer.MAX_VALUE)
public class DemoAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(
                "/user/regist", // 注册请求
                // org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController
                // BasicErrorController 类提供的默认错误信息处理服务
                "/error",
                "/connect/*",
                "/auth/*",
                "/signin",
                "/social/signUp",  // app注册跳转服务
                "/swagger-ui.html",
                "/swagger-ui.html/**",
                "/webjars/**",
                "/swagger-resources/**",
                "/v2/**"
        )
                .permitAll();
                // 这里配置了一个不存在的角色。
                // 可以访问下 看是否有效果
//                .antMatchers("/user/*").hasRole("xxx");

        config.anyRequest().access("@rbacService.hasPermission(request,authentication)");
    }
}
