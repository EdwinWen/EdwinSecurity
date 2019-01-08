package com.edwin.security.core.properties;

/**
 * Created by wenpuzhao on 2019/1/7.
 */
public class BrowserProperties {
    private  String loginPage="/imooc-signIn.html";
    private LoginResponseType loginType=LoginResponseType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginResponseType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginResponseType loginType) {
        this.loginType = loginType;
    }
}
