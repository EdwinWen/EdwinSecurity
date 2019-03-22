package com.edwin.security.core.properties;

/**
 * Created by wenpuzhao on 2019/3/13.
 */
public class OAuth2Properties {

    private String jwtSigningKey = "edwin";
    private OAuth2ClientProperties[] clients = {};

    public OAuth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(OAuth2ClientProperties[] clients) {
        this.clients = clients;
    }

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }
}
