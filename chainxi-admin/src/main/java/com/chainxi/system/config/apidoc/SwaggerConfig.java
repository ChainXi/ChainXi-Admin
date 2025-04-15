package com.chainxi.system.config.apidoc;

import com.github.xingfudeshi.knife4j.core.conf.GlobalConstants;
import com.github.xingfudeshi.knife4j.extend.filter.basic.AbstractSecurityFilter;
import com.github.xingfudeshi.knife4j.extend.filter.basic.JakartaServletSecurityBasicAuthFilter;
import com.github.xingfudeshi.knife4j.spring.configuration.Knife4jProperties;
import com.github.xingfudeshi.knife4j.spring.util.EnvironmentUtils;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    private static final String SECURITY_SCHEME_NAME = "token";
    private final Environment environment;


    /**
     * Security with Basic Http
     * @param knife4jProperties Basic Properties
     * @return BasicAuthFilter
     */
    @Bean
    @ConditionalOnMissingBean(JakartaServletSecurityBasicAuthFilter.class)
    @ConditionalOnExpression("!${knife4j.production:false} && ${knife4j.basic.enable:true}")
    public FilterRegistrationBean<JakartaServletSecurityBasicAuthFilter> securityBasicAuthFilter(Knife4jProperties knife4jProperties) {
        JakartaServletSecurityBasicAuthFilter authFilter = new JakartaServletSecurityBasicAuthFilter();
        if (knife4jProperties == null) {
            authFilter.setEnableBasicAuth(EnvironmentUtils.resolveBool(environment, "knife4j.basic.enable", Boolean.FALSE));
            authFilter.setUserName(EnvironmentUtils.resolveString(environment,
                    "knife4j.basic.username", GlobalConstants.BASIC_DEFAULT_USERNAME));
            authFilter.setPassword(EnvironmentUtils.resolveString(environment, "knife4j.basic.password", GlobalConstants.BASIC_DEFAULT_PASSWORD));
        } else {
            // 判断非空
            if (knife4jProperties.getBasic() == null) {
                authFilter.setEnableBasicAuth(Boolean.FALSE);
                authFilter.setUserName(GlobalConstants.BASIC_DEFAULT_USERNAME);
                authFilter.setPassword(GlobalConstants.BASIC_DEFAULT_PASSWORD);
            } else {
                authFilter.setEnableBasicAuth(knife4jProperties.getBasic().isEnable());
                authFilter.setUserName(knife4jProperties.getBasic().getUsername());
                authFilter.setPassword(knife4jProperties.getBasic().getPassword());
                authFilter.addRule(knife4jProperties.getBasic().getInclude());
            }
        }
        FilterRegistrationBean<JakartaServletSecurityBasicAuthFilter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(authFilter);
        registration.setOrder(AbstractSecurityFilter.SPRING_FILTER_ORDER);
        return registration;
    }

    @Bean
    public OpenAPI createOpenAPI() throws UnknownHostException {
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
                        .license(new License().name("MIT License")))
                .externalDocs(new ExternalDocumentation()
                        .description("ChainXi SpringDoc")
                        .url("https://chainxi.github.io/ChainXi-Admin-Doc/"))
                .components(new Components().securitySchemes(map));
        map
                .keySet()
                .forEach(key -> openAPI.addSecurityItem(new SecurityRequirement().addList(key)));
        InetAddress localHost = InetAddress.getLocalHost();
        String hostAddress = localHost.getHostAddress();
        int port = Integer.parseInt(environment.getProperty("server.port")); // 获取真实端口
        String scheme = environment.getProperty("server.ssl.enabled", "false").equalsIgnoreCase("true") ? "https" : "http";
        log.info("access: {}://{}:{}/doc.html#/home", scheme, hostAddress, port);
        return openAPI;
    }

    @Bean
    public GroupedOpenApi unknownApi() {
        return GroupedOpenApi.builder()
                .group("unknown")
                .pathsToMatch("/**")
                .build();
    }

}
