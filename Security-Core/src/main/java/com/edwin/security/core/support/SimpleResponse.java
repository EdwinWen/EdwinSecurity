package com.edwin.security.core.support;

/**
 * Created by wenpuzhao on 2019/3/11.
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
