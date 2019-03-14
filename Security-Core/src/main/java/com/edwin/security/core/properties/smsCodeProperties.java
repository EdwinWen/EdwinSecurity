package com.edwin.security.core.properties;

/**
 * Created by wenpuzhao on 2019/1/14.
 */
public class smsCodeProperties {
    private int length=6;
    private int expireIn=180;

    private String url;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
