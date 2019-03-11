package com.edwin.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * Created by wenpuzhao on 2019/2/1.
 */
public class QQProperties extends SocialProperties {
    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
