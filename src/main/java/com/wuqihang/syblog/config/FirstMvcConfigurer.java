package com.wuqihang.syblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FirstMvcConfigurer implements WebMvcConfigurer {
    private final FirstHandlerInterceptor firstHandlerInterceptor;

    public FirstMvcConfigurer(FirstHandlerInterceptor firstHandlerInterceptor) {
        this.firstHandlerInterceptor = firstHandlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(firstHandlerInterceptor)
                .addPathPatterns("/*")
                .addPathPatterns("/admin/*")
                .addPathPatterns("/api/*")
                .excludePathPatterns("/admin/signup")
                .excludePathPatterns("/admin/create-account");
    }
}
