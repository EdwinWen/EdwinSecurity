package com.edwin.security.core.properties;

/**
 * Created by wenpuzhao on 2019/1/10.
 */
public class ValidateCodeProperties {

    private ImageCodeProperties image=new ImageCodeProperties();

    private smsCodeProperties sms=new smsCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }

    public smsCodeProperties getSms() {
        return sms;
    }

    public void setSms(smsCodeProperties sms) {
        this.sms = sms;
    }
}
