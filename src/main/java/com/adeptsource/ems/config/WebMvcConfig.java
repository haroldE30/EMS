package com.adeptsource.ems.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.adeptsource.ems.common.bind.DTOMethodProcessor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new DTOMethodProcessor());
    }
    
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//    	registry.addMapping("/api/v1/**")
//    	.allowedOrigins("http://localhost:4200")
//    	.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
//    }
    
}
