package com.wuqihang.syblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Wuqihang
 */
@Configuration
public class SYMvcConfigurer implements WebMvcConfigurer {
    private final SYHandlerInterceptor syHandlerInterceptor;

    public SYMvcConfigurer(SYHandlerInterceptor syHandlerInterceptor) {
        this.syHandlerInterceptor = syHandlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(syHandlerInterceptor)
                .addPathPatterns("/api/*")
                .excludePathPatterns("/api/get-token");
    }
}
