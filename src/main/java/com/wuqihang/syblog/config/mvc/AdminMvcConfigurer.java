package com.wuqihang.syblog.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdminMvcConfigurer implements WebMvcConfigurer {
    private final AdminHandlerInterceptor adminHandlerInterceptor;

    public AdminMvcConfigurer(AdminHandlerInterceptor adminHandlerInterceptor) {
        this.adminHandlerInterceptor = adminHandlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminHandlerInterceptor)
                .addPathPatterns("/admin/**").addPathPatterns("/admin")
                .excludePathPatterns("/admin/sign")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/signup")
                .excludePathPatterns("/admin/create-account");
    }
}
