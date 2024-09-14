package org.itmo_olesia.filesystemhelper.Configurations;


import org.springdoc.core.models.GroupedOpenApi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                //.packagesToScan("org.itmo_olesia.multithreading.сontrollers") // Укажите пакет, содержащий ваши контроллеры
                .pathsToMatch("/mongo/**")
                .build();
    }
}

