package com.edwin.security.core.validate.code.image;

import com.edwin.security.core.validate.code.ValidateCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * Created by wenpuzhao on 2019/1/8.
 */
public class ImageCode extends ValidateCode {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireIn)
    {
        super(code,expireIn);
        this.image = image;
        logger.info("图形验证码为："+code);
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
