package com.wzh.myshop.web.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wzh
 * @date 2019/10/9 - 11:35
 */
@MapperScan("com.wzh.myshop.mapper")
@SpringBootApplication(scanBasePackages = "com.wzh.myshop")
public class WebApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApiApplication.class,args);
    }
}
