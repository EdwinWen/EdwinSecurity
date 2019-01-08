package com.edwin.security.browser.support;

/**
 * Created by wenpuzhao on 2019/1/7.
 */
public class SimpleResponse {
    private  Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
