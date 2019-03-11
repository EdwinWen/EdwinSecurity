package com.edwin.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by wenpuzhao on 2019/1/10.
 */
public interface ValidateCodeGenerator {
    ValidateCode generate(ServletWebRequest request);
}
