package com.growup.growthmate.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "HANEUM GROWTH-MATE API 명세서",
                description = "HANEUM GROWTH-MATE BE API 명세서입니다.",
                version = "v1")
)
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi stOpenApi() {

        return GroupedOpenApi.builder()
                .group("HANEUM GROWTH-MATE API v1")
                .pathsToMatch("/**")
                .build();
    }
}

