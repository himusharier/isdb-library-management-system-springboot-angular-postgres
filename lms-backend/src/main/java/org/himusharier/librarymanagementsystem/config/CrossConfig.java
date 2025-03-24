package org.himusharier.librarymanagementsystem.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CrossConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:4500")
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

}
