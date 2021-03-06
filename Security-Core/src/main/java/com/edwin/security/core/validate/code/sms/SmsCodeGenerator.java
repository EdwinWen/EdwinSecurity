package com.edwin.security.core.validate.code.sms;

import com.edwin.security.core.properties.SecurityProperties;
import com.edwin.security.core.validate.code.ValidateCode;
import com.edwin.security.core.validate.code.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by wenpuzhao on 2019/1/14.
 */
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code= RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        code="000000";
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }



    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}