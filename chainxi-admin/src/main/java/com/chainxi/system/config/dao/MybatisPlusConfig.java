package com.chainxi.system.config.dao;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.chainxi.system.handler.SysDBFieldHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MybatisPlusConfig {
    @Bean
    public MetaObjectHandler sysMetaObjectHandler() {
        return new SysDBFieldHandler(); // 自动填充参数类
    }
}