package com.xnara.xchange.locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class LocateConfig {

    @Bean(name = "texts")
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource bundle = new ResourceBundleMessageSource();
        bundle.setBasename("texts");
        bundle.setDefaultEncoding("UTF-8");
        bundle.setUseCodeAsDefaultMessage(true);

        return bundle;
    }
}
