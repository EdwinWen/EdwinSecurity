package com.edwin.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by wenpuzhao on 2019/1/7.
 */
// 这个类会读取配置文件中以edwin.secutity 开头到所有配置项
@ConfigurationProperties(prefix = "edwin.security")
public class SecurityProperties {
    private BrowserProperties browser= new BrowserProperties();


    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }


}
