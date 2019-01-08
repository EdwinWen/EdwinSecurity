package com.edwin.security.browser;

import com.edwin.security.browser.authentication.ImoocAuthenticationSuccessHandler;
import com.edwin.security.core.properties.SecurityProperties;
import com.edwin.security.core.validate.core.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by wenpuzhao on 2019/1/7.
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler imoocAuthenctiationFailureHandler;


    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(imoocAuthenctiationFailureHandler);
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/require")// 自定义的登录页面，通过一个controller来进行页面跳转，进而判断是否为html登录
                .loginProcessingUrl("/authentication/form") // 自定义前端提交对 action 为登录身份验证
                .successHandler(imoocAuthenticationSuccessHandler)// 自定义登录成功，返回restful风格到状态码
                .failureHandler(imoocAuthenctiationFailureHandler)// 自定义登录失败，返回restful风格到状态码
                .and().authorizeRequests()
                .antMatchers("/authentication/require"
                        ,securityProperties.getBrowser().getLoginPage()
                        ,"/code/image"
                ).permitAll()// 对自定义对登录页面，springsecurity 不进行拦截
                .anyRequest().authenticated()
        .and().csrf().disable();
    }

    // 用户名密码进行加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
