package com.edwin.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by wenpuzhao on 2019/3/25.
 */
@SpringBootApplication
@EnableEurekaServer // EurekaServer服务器端启动类,接受其它微服务注册进来
public class EurekaServer_App {
    public static void main(String[] args)
    {
        SpringApplication.run(EurekaServer_App.class, args);
    }
}
