package com.edwin.security.core.validate.code;

import com.edwin.security.core.properties.SecurityConstants;
import com.edwin.security.core.validate.code.image.ImageCode;
import com.edwin.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wenpuzhao on 2019/1/8.
 */
@RestController
public class ValidateCodeController {
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
    //    public static final String SESSION_KEY = "SESSION_IMAGE_CODE";
//
    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();
    //
//    @Autowired
//    private ValidateCodeGenerator imageCodeGenerator;
//
//    @Autowired
//    private ValidateCodeGenerator smsCodeGenerator;
//
//    @Autowired
//    private SmsCodeSender smsCodeSender;
//
//    @GetMapping("/code/image")
//    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        ImageCode imageCode = (ImageCode) imageCodeGenerator.generate(new ServletWebRequest(request));
//        sessionStrategy.setAttribute(new ServletRequestAttributes(request),SESSION_KEY,imageCode);
//        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
//    }
//
//    @GetMapping("/code/sms")
//    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
//        ValidateCode smsCode =smsCodeGenerator.generate(new ServletWebRequest(request));
//        sessionStrategy.setAttribute(new ServletRequestAttributes(request),SESSION_KEY,smsCode);
//        String mobile = ServletRequestUtils.getRequiredStringParameter(request,"mobile");
//        // 不同供应商提供不同的接口
//        smsCodeSender.send(mobile,smsCode.getCode());
//    }
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 创建验证码，根据验证码类型不同，调用不同的 {@link ValidateCodeProcessor}接口实现
     *
     * @param request
     * @param response
     * @param type
     * @throws Exception
     */
    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public ValidateCode createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
            throws Exception {
        // 去除验证码返回的值
        validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
        ValidateCode code=(ValidateCode)sessionStrategy.getAttribute(new ServletRequestAttributes(request), "SESSION_KEY_FOR_CODE_SMS");
        return code;
    }

}