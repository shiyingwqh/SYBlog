package com.wuqihang.syblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
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

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }
}
