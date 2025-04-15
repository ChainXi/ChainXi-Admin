package com.chainxi.generator.config;

import com.chainxi.common.web.core.YamlPropertySourceFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * 读取代码生成相关配置
 *
 * @author chainxi
 */
@AutoConfiguration
@EnableConfigurationProperties(GenProperties.class)
@PropertySource(value = {"classpath:generator.yml"}, factory =
        YamlPropertySourceFactory.class)
@ComponentScan("com.chainxi.generator")
@MapperScan({"com.chainxi.generator.mapper"})
public class GenConfig {}
