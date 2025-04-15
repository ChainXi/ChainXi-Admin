package com.chainxi.generator.config;

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
@ComponentScan("com.chainxi")
@MapperScan({"com.chainxi.generator.mapper","com.chainxi.system.mapper"})
@PropertySource(value = {"classpath:generator.yml"})
public class GenConfig {}
