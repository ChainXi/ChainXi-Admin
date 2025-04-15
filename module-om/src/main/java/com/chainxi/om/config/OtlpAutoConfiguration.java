package com.chainxi.om.config;

import com.chainxi.common.web.core.YamlPropertySourceFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.PropertySource;

//https://www.cnblogs.com/misakivv/p/18059352#tid-YnbDD7
//https://www.bilibili.com/video/BV1NwNVemEyE/?spm_id_from=333.337.search-card.all.click&vd_source=78b4e7708ce0b9d13c931c403cb92e33
//https://help.aliyun.com/zh/opentelemetry/user-guide/use-opentelemetry-to-submit-trace-data-of-java-applications#f4148c817ez0t
//https://juejin.cn/post/7229186336154419259
/*-javaagent:H:/Project/ChainXi-Admin/module-om/src/main/resources/opentelemetry-javaagent.jar
        -Dotel.metrics.exporter=prometheus
        -Dotel.traces.exporter=none
        -Dotel.logs.exporter=none*/
/**
 * https://opentelemetry.io/docs/languages/java/configuration/
 * @Author : ChainXi
 * @Date : 2025/5/3 1:00
 * @Desc :MinimizedImpl
 */
@AutoConfiguration
@PropertySource(value = {"classpath:otel.yml"}, factory = YamlPropertySourceFactory.class)
public class OtlpAutoConfiguration {
}
