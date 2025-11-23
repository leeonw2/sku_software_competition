package com.example.soft_backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Backend API 명세서",
                version = "v1.0.0",
                description = "공모전 프로젝트 API 명세서"
        )
)
public class SwaggerConfig {
}
