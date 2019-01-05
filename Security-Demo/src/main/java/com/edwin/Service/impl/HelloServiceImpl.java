package com.edwin.Service.impl;

import com.edwin.Service.HelloService;
import org.springframework.stereotype.Service;

/**
 * Created by wenpuzhao on 2019/1/5.
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String greeting(String name) {
        System.out.println("greeting");
        return "hello "+name;
    }
}
