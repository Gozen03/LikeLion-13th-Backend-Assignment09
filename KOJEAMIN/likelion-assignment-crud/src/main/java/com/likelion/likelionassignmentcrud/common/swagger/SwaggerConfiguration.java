package com.likelion.likelionassignmentcrud.common.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI swaggerConfig() {

        // 1. 프로젝트에 맞는 정보로 수정
        Info info = new Info()
                .title("Likelion Assignment CRUD API")
                .description("멋쟁이사자처럼 과제: 음식점 및 메뉴 관리 CRUD 애플리케이션입니다.")
                .version("1.0.0");

        // 2. 서버 정보 (로컬 환경)
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("Development Server");

        // 3. SecurityScheme (기존 스타일 유지)
        Components components = new Components()
                .addSecuritySchemes("bearer-key", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT"));

        // 4. SecurityRequirement
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("bearer-key");

        // 5. OpenAPI 최종 반환
        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer))
                .components(components)
                .addSecurityItem(securityRequirement);
    }
}