package com.wuqihang.syblog.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Wuqihang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "syblog.config")

public class SYConfiguration {
    @Value("864000")
    private int tokenKeepTime;
    @Value("")
    private String key;
    @Value("")
    private Byte[] bytes;
    @Value("10")
    private int numOnePage;
    @Value("SYBlog")
    private String title;
    @Value("SYBlog")
    private String appName;
}
