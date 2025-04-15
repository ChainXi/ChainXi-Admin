package com.chainxi.system.config.oss;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <a href="https://blog.csdn.net/qq_51808107/article/details/131299958">SpringBoot集成阿里云OSS实现图片存储服务</a>
 */
@Configuration
@EnableConfigurationProperties(OssProperties.class)
public class OssConfig {
    @Bean
    public OssTemplate ossTemplate(OssProperties properties) {
        return new OssTemplate(properties);
    }
}
