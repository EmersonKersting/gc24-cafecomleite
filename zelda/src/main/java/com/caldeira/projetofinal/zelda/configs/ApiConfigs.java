package com.caldeira.projetofinal.zelda.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiConfigs {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
