package com.edwin.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by wenpuzhao on 2019/1/7.
 */
// 这个类会读取配置文件中以edwin.secutity 开头到所有配置项
@ConfigurationProperties(prefix = "edwin.security")
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    private SocialProperties social = new SocialProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }
}
