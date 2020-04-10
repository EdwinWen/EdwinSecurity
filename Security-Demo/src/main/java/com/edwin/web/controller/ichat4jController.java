package com.edwin.web.controller;


import com.edwin.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wenpuzhao on 2019/3/31.
 */
@RestController
@RequestMapping("/itchat4j")
public class ichat4jController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private RequestCache requestCache=new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();

    @GetMapping("/login")
    public void regist(User user, HttpServletRequest request, HttpServletResponse response) {

        logger.info("iiiii");
        SavedRequest savedRequest = requestCache.getRequest(request, response);


                try {
                    redirectStrategy.sendRedirect(request, response, "/wechat.html");
                }catch (Exception ex){

                }


    }

    @GetMapping("/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {


        //读取本地图片输入流
        FileInputStream inputStream = new FileInputStream("/Users/wenpuzhao/Downloads/itchat4j/login/QR.jpg");
        int i = inputStream.available();
        //byte数组用于存放图片字节数据
        byte[] buff = new byte[i];
        inputStream.read(buff);
        //记得关闭输入流
        inputStream.close();
        //设置发送到客户端的响应内容类型
        response.setContentType("image/*");
        OutputStream out = response.getOutputStream();
        out.write(buff);
        //关闭响应输出流
        out.close();
    }
}