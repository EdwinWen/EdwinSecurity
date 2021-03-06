package com.edwin.security.core.properties;

/**
 * Created by wenpuzhao on 2019/1/23.
 */
public class SocialProperties {
    private QQProperties qq = new QQProperties();
    private String filterProcessesUrl = "/auth";
    private WeixinProperties weixin = new WeixinProperties();

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    public WeixinProperties getWeixin() {
        return weixin;
    }

    public void setWeixin(WeixinProperties weixin) {
        this.weixin = weixin;
    }
}
