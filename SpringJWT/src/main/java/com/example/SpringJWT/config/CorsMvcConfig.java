package com.example.SpringJWT.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//다른 controller단 문제도 처리해주기 위해서
public class CorsMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

        corsRegistry.addMapping("/**") //모든 우리의 controller 경로에 대해서
                .allowedOrigins("http://localhost:3000");
    }
}
