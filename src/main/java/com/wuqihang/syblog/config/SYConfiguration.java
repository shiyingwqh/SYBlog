package com.wuqihang.syblog.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * @author Wuqihang
 */
@Data
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
    private String themeDir;

    private ITemplateResolver resolver;

    @Bean
    public ITemplateResolver themeTemplateResolver(ThymeleafProperties properties) {
        FileTemplateResolver resolver = new FileTemplateResolver();
        this.resolver = resolver;
        resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
        resolver.setCacheable(properties.isCache());
        resolver.setSuffix(properties.getSuffix());
        if (properties.getEncoding() != null) {
            resolver.setCharacterEncoding(properties.getEncoding().name());
        }
        resolver.setTemplateMode(properties.getMode());
        resolver.setPrefix(System.getProperty("user.dir") + "/templates/");
        return resolver;
    }

    public ITemplateResolver getThemeResolver() {
        return resolver;
    }
}
