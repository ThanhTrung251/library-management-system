package com.example.lib.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MVCConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/admin_lte/dist/**","/admin_lte/plugins/**","/admin_lte/js/**")
                .addResourceLocations("classpath:/static/admin_lte/dist/","classpath:/static/admin_lte/plugins/","classpath:/static/admin_lte/js/");
    }
}
