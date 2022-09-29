package com.wuqihang.syblog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * @author Wuqihang
 */
@Configuration
@EnableConfigurationProperties(SYConfiguration.class)
public class InitConfiguration implements InitializingBean {
    @Value("#{SYConfiguration.appName}")
    private String appName;
    private final Logger logger = LoggerFactory.getLogger(InitConfiguration.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        File file = new File("~", appName);
        File staticF = new File(file, "static");
        if (!file.exists() || !staticF.exists()) {
            file.mkdir();
            staticF.mkdir();
            logger.info("Create New File Folder!");
        }
    }

}
