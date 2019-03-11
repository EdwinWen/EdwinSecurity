package com.edwin.security.core.validate.code.sms;

/**
 * Created by wenpuzhao on 2019/1/14.
 */
public interface SmsCodeSender {
    void send(String mobile, String code);

}
