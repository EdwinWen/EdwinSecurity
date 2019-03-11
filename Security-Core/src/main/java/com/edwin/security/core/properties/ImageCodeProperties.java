package com.edwin.security.core.properties;

/**
 * Created by wenpuzhao on 2019/1/10.
 */
public class ImageCodeProperties extends smsCodeProperties {
    private int width=67;
    private int height=24;

    private String url;


    public ImageCodeProperties(){
        setLength(4);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
