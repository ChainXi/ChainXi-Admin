package com.chainxi.system.config.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 用于配置不需要保护的资源路径
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "chainxi.security")
public class SecurityIgnoreUrlsProperties {
    private List<String> urls = List.of();
}
