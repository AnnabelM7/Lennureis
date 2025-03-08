package com.cgi2025.lennuplaan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FlightConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
