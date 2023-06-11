package com.example.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ImageConfig {

    private static Logger log = LoggerFactory.getLogger(ImageConfig.class);

    public ImageConfig() {
        log.info("ImageConfig object created: {}", this);
    }

    @Bean
    public RestTemplate getTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        log.info("Inside getTemplate function: creating an object of restTemplate: {}",restTemplate);
        return restTemplate;
    }
}
