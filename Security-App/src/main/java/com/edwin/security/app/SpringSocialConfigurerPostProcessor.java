package com.edwin.security.app;

import com.edwin.security.core.social.ImoocSpringSocialConfigurer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 所有Bean初始化之前和初始化后都要经过这个
 * 需要用户注册时不要跳转到之前浏览器情况下注册页面
 * 这里配置是将跳转到/social/signUp的服务上面
 *
 *
 * Created by wenpuzhao on 2019/3/12.
 */
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (StringUtils.equals(beanName, "imoocSocialSecurityConfig")) {
            ImoocSpringSocialConfigurer configurer = (ImoocSpringSocialConfigurer)bean;
            configurer.signupUrl("/social/signUp");
            return configurer;
        }
        return bean;
    }
}
