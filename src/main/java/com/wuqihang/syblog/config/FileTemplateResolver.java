package com.wuqihang.syblog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.templateresolver.AbstractConfigurableTemplateResolver;
import org.thymeleaf.templateresource.FileTemplateResource;
import org.thymeleaf.templateresource.ITemplateResource;

import java.util.Map;

/**
 * @author Wuqihang
 */
public class FileTemplateResolver extends AbstractConfigurableTemplateResolver {
    Logger logger = LoggerFactory.getLogger(FileTemplateResolver.class);
    @Override
    protected ITemplateResource computeTemplateResource(IEngineConfiguration configuration, String ownerTemplate, String template, String resourceName, String characterEncoding, Map<String, Object> templateResolutionAttributes) {
        FileTemplateResource resource = new FileTemplateResource(resourceName, characterEncoding);
        if (resource.exists()){
            return resource;
        }
        logger.info(resourceName + " is Not Found!");
        return null;
    }
}
