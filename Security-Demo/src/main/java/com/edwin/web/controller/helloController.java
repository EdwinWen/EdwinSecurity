package com.edwin.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wenpuzhao on 2019/1/5.
 */
@RestController
public class helloController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello,secutity!";
    }
}
