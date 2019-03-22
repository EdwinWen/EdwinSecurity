package com.edwin.security.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * Created by wenpuzhao on 2019/3/21.
 */
public interface AuthorizeConfigProvider {
    /**
     * @param config
     * @see HttpSecurity#authorizeRequests()
     */
    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);

}
