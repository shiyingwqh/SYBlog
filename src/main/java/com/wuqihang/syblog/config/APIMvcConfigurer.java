package com.wuqihang.syblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Wuqihang
 */
@Configuration
public class APIMvcConfigurer implements WebMvcConfigurer {
    private final APIHandlerInterceptor syHandlerInterceptor;

    public APIMvcConfigurer(APIHandlerInterceptor syHandlerInterceptor, AdminHandlerInterceptor adminHandlerInterceptor, FirstHandlerInterceptor firstHandlerInterceptor) {
        this.syHandlerInterceptor = syHandlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(syHandlerInterceptor)
                .addPathPatterns("/api/*")
                .excludePathPatterns("/api/token/*")
                .excludePathPatterns("api/create-account");
    }
}
