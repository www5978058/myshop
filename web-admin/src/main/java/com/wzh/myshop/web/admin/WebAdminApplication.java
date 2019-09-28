package com.wzh.myshop.web.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wzh
 * @date 2019/9/22 - 11:24
 */
@MapperScan("com.wzh.myshop.web.admin.mapper")
@SpringBootApplication(scanBasePackages = "com.wzh.myshop")
public class WebAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebAdminApplication.class,args);
    }
}
