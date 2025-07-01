package org.example.wattpadclone1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/assets/image/**")
                .addResourceLocations(
                        "classpath:/static/assets/image/",      // for static images in project
                        "file:/C:/wattpad-uploads/"              // for uploaded images
                );
    }
}

