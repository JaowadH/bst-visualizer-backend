package com.example.bst.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(
                        "https://<your-firebase-app>.web.app",
                        "https://<your-firebase-app>.firebaseapp.com"
                )
                .allowedMethods("GET", "POST", "OPTIONS");
    }
}