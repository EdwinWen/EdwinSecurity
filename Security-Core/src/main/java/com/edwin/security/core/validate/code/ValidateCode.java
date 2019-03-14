package com.edwin.security.core.validate.code;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by wenpuzhao on 2019/1/14.
 */
public class ValidateCode implements Serializable {
    private String code;
    private LocalDateTime expireTime;

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
    public ValidateCode( String code, int expireIn)
    {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
