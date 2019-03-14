package com.edwin.security.app.social.impl;

import com.edwin.security.core.social.SocialAuthenticationFilterPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * Created by wenpuzhao on 2019/3/12.
 */
@Component
public class AppSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor {
    @Autowired
    private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

    /**
     * @see cn.mrcode.imooc.springsecurity.securitycore.social.SocialAuthenticationFilterPostProcessor.process
     */
    @Override
    public void process(SocialAuthenticationFilter socialAuthenticationFilter) {
        // 这里设置的其实就是之前  重构用户名密码登录里面实现的 MyAuthenticationSuccessHandler
        socialAuthenticationFilter.setAuthenticationSuccessHandler(imoocAuthenticationSuccessHandler);
    }
}
