package com.chainxi.system.config.apidoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Slf4j
@Configuration
public class SwaggerConfig {
    private static final String SECURITY_SCHEME_NAME = "token";

    @Bean
    public OpenAPI createOpenAPI() {
        HashMap<String, SecurityScheme> map = new HashMap<>();
        map.put(SECURITY_SCHEME_NAME,
                new SecurityScheme()
                        .type(SecurityScheme.Type.APIKEY)
                        .in(SecurityScheme.In.HEADER)
                        .name(SECURITY_SCHEME_NAME));
        OpenAPI openAPI = new OpenAPI()
                .info(new Info()
                        .title("ChainXi API")
                        .description("SpringDoc API")
                        .version("V1.0.0")
                        .license(new License().name("Apache 2.0").url("https://www.baidu.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("ChainXi SpringDoc")
                        .url("https://www.baidu.com"))
                .components(new Components().securitySchemes(map));
        map
                .keySet()
                .forEach(key -> openAPI.addSecurityItem(new SecurityRequirement().addList(key)));
        return openAPI;
    }

    @Bean
    public GroupedOpenApi unknownApi() {
        log.info("http://localhost:8080/doc.html#/home");
        return GroupedOpenApi.builder()
                .group("unknown")
                .pathsToMatch("/**")
                .build();
    }

//    @Bean
//    public GroupedOpenApi adminApi() {
//        return GroupedOpenApi.builder()
//                .group("admin")
//                .pathsToMatch("/admin/**")
//                .build();
//    }

}
