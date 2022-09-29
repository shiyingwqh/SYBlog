package com.wuqihang.syblog.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "syblog.theme")
public class ThemeConfiguration {
    public String dir;
    private ITemplateResolver resolver;

    @Bean
    public ITemplateResolver fileTemplateResolver(ThymeleafProperties properties) {
        FileTemplateResolver resolver = new FileTemplateResolver();
        this.resolver = resolver;
        resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
        resolver.setCacheable(properties.isCache());
        resolver.setSuffix(properties.getSuffix());
        if (properties.getEncoding() != null) {
            resolver.setCharacterEncoding(properties.getEncoding().name());
        }
        resolver.setTemplateMode(properties.getMode());
        if (dir == null){
            resolver.setPrefix(System.getProperty("user.dir") + "/templates/");
        } else {
            resolver.setPrefix(dir);
        }
        return resolver;
    }

    public ITemplateResolver getResolver() {
        return resolver;
    }
}
