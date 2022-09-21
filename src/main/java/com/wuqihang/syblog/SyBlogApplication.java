package com.wuqihang.syblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.thymeleaf.spring5.context.SpringContextUtils;

@SpringBootApplication
public class SyBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SyBlogApplication.class, args);
    }

}
