package com.chainxi.system.config.tenant;

import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.chainxi.system.handler.TenantDatabaseHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "chainxi.tenant", value = "enable", matchIfMissing = false)
@EnableConfigurationProperties(TenantProperties.class)
public class TenantAutoConfiguration {


    // ========== DB ==========

    @Bean
    public TenantLineInnerInterceptor tenantLineInnerInterceptor(TenantProperties properties) {
        return new TenantLineInnerInterceptor(new TenantDatabaseHandler(properties));
    }


}
