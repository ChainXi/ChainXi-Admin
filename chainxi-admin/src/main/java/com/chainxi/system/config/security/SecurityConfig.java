package com.chainxi.system.config.security;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@EnableConfigurationProperties(SecurityIgnoreUrlsProperties.class)
public class SecurityConfig {
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationFilter authenticationFilter;
    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;
    private final LogoutSuccessHandler logoutSuccessHandler;
    private final List<RequestMappingHandlerMapping> requestMappingHandlerMapping;
    private final SecurityIgnoreUrlsProperties securityIgnoreUrlsProperties;
    private final AuthorizationManager<RequestAuthorizationContext> authenticated;

    @Bean
    public SimpleModule requestMappingInfoDeserializer() {
        return new SimpleModule().addKeyDeserializer(RequestMappingInfo.class,
                RequestMappingInfoKeyDeserializer.INSTANCE);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain configure(UserDetailsService userDetailsService,AuthenticationManager authenticationManager,
            HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .userDetailsService(userDetailsService)
                .cors(httpSecurityCorsConfigurer -> {
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowCredentials(true);
                    corsConfiguration.addAllowedHeader("*");
                    corsConfiguration.addAllowedMethod("*");
                    corsConfiguration.addAllowedOriginPattern("*");
                    source.registerCorsConfiguration("/**",corsConfiguration);
                    httpSecurityCorsConfigurer.configurationSource(source);
                })
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        // 获得 @PermitAll 带来的 URL 列表，免登录
        Multimap<HttpMethod, String> permitAllUrls = getPermitAllUrlsFromAnnotations();
        httpSecurity.authorizeHttpRequests(registry -> {
            registry
                    .requestMatchers(securityIgnoreUrlsProperties
                            .getUrls()
                            .toArray(new String[0]))
                    .permitAll();
            registry
                    .requestMatchers(HttpMethod.GET, permitAllUrls
                            .get(HttpMethod.GET)
                            .toArray(new String[0]))
                    .permitAll();
            registry
                    .requestMatchers(HttpMethod.POST, permitAllUrls
                            .get(HttpMethod.POST)
                            .toArray(new String[0]))
                    .permitAll();
            registry
                    .requestMatchers(HttpMethod.PUT, permitAllUrls
                            .get(HttpMethod.PUT)
                            .toArray(new String[0]))
                    .permitAll();
            registry
                    .requestMatchers(HttpMethod.DELETE, permitAllUrls
                            .get(HttpMethod.DELETE)
                            .toArray(new String[0]))
                    .permitAll();
            registry
                    .anyRequest()
                    .access(authenticated);
        });
        //添加过滤器
        httpSecurity
                .addFilterBefore(authenticationFilter,
                        UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(configurer -> {
                    //配置认证失败处理器
                    configurer
                            .authenticationEntryPoint(authenticationEntryPoint)
                            .accessDeniedHandler(accessDeniedHandler);
                })
                .formLogin(configurer -> {
                    //配置认证成功处理器
                    configurer.successHandler(successHandler)
                            //配置认证失败处理器
                            .failureHandler(failureHandler);

                })
                .logout(configurer -> {
                    //配置注销成功处理器
                    configurer.logoutSuccessHandler(logoutSuccessHandler);

                });

        httpSecurity.authenticationManager(authenticationManager);

        return httpSecurity.build();
    }

    private Multimap<HttpMethod, String> getPermitAllUrlsFromAnnotations() {
        Multimap<HttpMethod, String> result = HashMultimap.create();
        // 获得有 @PermitAll 注解的接口
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : requestMappingHandlerMapping
                .stream()
                .map(AbstractHandlerMethodMapping::getHandlerMethods)
                .flatMap(requestMappingInfoHandlerMethodMap -> requestMappingInfoHandlerMethodMap
                        .entrySet()
                        .stream())
                .collect(Collectors.toSet())) {
            HandlerMethod handlerMethod = entry.getValue();
            if (!handlerMethod.hasMethodAnnotation(PermitAll.class)) {
                continue;
            }
            Set<String> urls = entry
                    .getKey()
                    .getPatternValues();
            // 根据请求方法，添加到 result 结果
            entry
                    .getKey()
                    .getMethodsCondition()
                    .getMethods()
                    .forEach(requestMethod -> {
                        switch (requestMethod) {
                            case GET -> result.putAll(HttpMethod.GET, urls);
                            case POST -> result.putAll(HttpMethod.POST, urls);
                            case PUT -> result.putAll(HttpMethod.PUT, urls);
                            case DELETE -> result.putAll(HttpMethod.DELETE, urls);
                        }
                    });
        }
        return result;
    }

}
