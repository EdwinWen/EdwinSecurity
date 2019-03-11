package com.edwin.security.core.validate.code;

import com.edwin.security.core.properties.SecurityProperties;
import com.edwin.security.core.validate.code.image.ImageCodeGenerator;
import com.edwin.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.edwin.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wenpuzhao on 2019/1/10.
 *
 * 此类就是看有没有其它类继承的接口实现，来覆盖默认的实现类 **ConditionalOnMissingBean
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name="imageCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}
