package com.chainxi.common.web.config;

import com.chainxi.common.web.aspect.WebLogAspect;
import com.chainxi.common.web.component.ContextLoader;
import com.chainxi.common.web.core.databind.*;
import com.chainxi.common.web.handler.GlobalExceptionHandler;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ByteArraySerializer;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.Nonnull;
import java.io.IOException;
import java.io.Serial;
import java.security.KeyPair;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <a href="https://blog.csdn.net/q283614346/article/details/131884290">springboot2优雅个性化定制Jackson配置</a>
 */
@EnableWebMvc
@EnableAdminServer
@AutoConfiguration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer, Jackson2ObjectMapperBuilderCustomizer,
        Ordered {

    @Bean
    public GlobalExceptionHandler exceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    public ContextLoader setContextLoader(@Nonnull ApplicationContext applicationContext) {
        ContextLoader contextLoader = new ContextLoader();
        contextLoader.setApplicationContext(applicationContext);
        return contextLoader;
    }

    @Override
    public void extendMessageConverters(@Nonnull List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> converter : converters) {
            if (!(converter instanceof AbstractJackson2HttpMessageConverter)) {
                continue;
            }
            AbstractJackson2HttpMessageConverter jackson2HttpMessageConverter =
                    (AbstractJackson2HttpMessageConverter) converter;
            jackson2HttpMessageConverter.setObjectMapper(jackson2HttpMessageConverter
                    .getObjectMapper()
                    .registerModule(new SimpleModule()
                            .addSerializer(Long.class, NumberSerializer.INSTANCE)
                            .addSerializer(Long.TYPE, NumberSerializer.INSTANCE)));
        }
    }

    @Bean
    public SimpleModule httpModule() {
        return new SimpleModule()
                .addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE)
                .addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE)
                .addSerializer(KeyPair.class, KeyPairSerializer.INSTANCE)
                .addDeserializer(KeyPair.class, KeyPairDeserializer.INSTANCE);
    }

    @Bean
    public WebLogAspect webLogAspect(ObjectMapper objectMapper) {
        objectMapper = objectMapper.copy();
        objectMapper.registerModule(new SimpleModule().addSerializer(byte[].class, new ByteArraySerializer() {
            @Serial
            private static final long serialVersionUID = -1L;

            @Override
            public void serialize(byte[] value,
                    JsonGenerator g,
                    SerializerProvider provider) throws IOException {
                if (value.length >= 1024*2) {
                    g.writeString(String.format("%s size: %d",
                            value.getClass().getSimpleName(),
                            value.length));
                }else {
                    super.serialize(value, g, provider);
                }
            }
        }));
//        objectMapper.setConfig();
//        ByteArraySerializer
        return new WebLogAspect(objectMapper);
    }

    @Override
    public void customize(Jackson2ObjectMapperBuilder builder) {
        builder.featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS,
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_INDEX);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}