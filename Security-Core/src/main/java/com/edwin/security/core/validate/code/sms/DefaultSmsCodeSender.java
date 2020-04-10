package com.edwin.security.core.validate.code.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wenpuzhao on 2019/1/14.
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void send(String mobile, String code) {
        System.out.println();
        logger.info("向手机"+mobile+"发送短信验证码"+code);
        logger.error("测试Debug日志");
    }
}
