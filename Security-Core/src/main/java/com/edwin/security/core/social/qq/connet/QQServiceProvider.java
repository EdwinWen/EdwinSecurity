package com.edwin.security.core.social.qq.connet;

import com.edwin.security.core.social.qq.api.QQ;
import com.edwin.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * Created by wenpuzhao on 2019/2/1.
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private  String appId;

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId,String appSecret) {
        super(new QQOAuth2Template(appId,appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accesstoken) {
        return new QQImpl(accesstoken,appId);
    }
}
