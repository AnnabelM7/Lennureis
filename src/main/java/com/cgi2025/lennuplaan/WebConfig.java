package com.cgi2025.lennuplaan;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
        //Ai kood
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")  // Lubab kõik aadressid
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Lubatud HTTP meetodid
                .allowedHeaders("*");  // Lubab kõik päised
    }
}