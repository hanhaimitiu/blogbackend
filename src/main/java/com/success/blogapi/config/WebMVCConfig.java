package com.success.blogapi.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry){



        registry.addMapping("/**")
                .allowCredentials(false)
                .allowedMethods("POST","GET","DELETE","PUT","OPTIONS")
                .allowedOrigins("*");
    }




}
