package com.edwin.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * Created by wenpuzhao on 2019/3/12.
 */
public interface SocialAuthenticationFilterPostProcessor {
    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}
