package com.wuqihang.syblog.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Wuqihang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "syblog.user.config")

public class UserConfiguration {
    private long tokenKeepTime;
    @Value("")
    private String key;
    @Value("")
    private Byte[] bytes;
}
