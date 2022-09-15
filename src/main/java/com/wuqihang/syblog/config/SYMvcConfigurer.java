package com.wuqihang.syblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author Wuqihang
 */
@Configuration
public class SYMvcConfigurer implements WebMvcConfigurer {
    private final APIHandlerInterceptor syHandlerInterceptor;
    private final AdminHandlerInterceptor adminHandlerInterceptor;

    public SYMvcConfigurer(APIHandlerInterceptor syHandlerInterceptor, AdminHandlerInterceptor adminHandlerInterceptor) {
        this.syHandlerInterceptor = syHandlerInterceptor;
        this.adminHandlerInterceptor = adminHandlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(syHandlerInterceptor)
                .addPathPatterns("/api/*")
                .excludePathPatterns("/api/get-token");
        registry.addInterceptor(adminHandlerInterceptor)
                .addPathPatterns("/admin/*")
                .excludePathPatterns("/admin/sign")
                .excludePathPatterns("/admin/login");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
