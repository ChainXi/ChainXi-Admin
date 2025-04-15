package com.chainxi.common.web.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.chainxi.common.web.core.databind.LocalDateTimeDeserializer;
import com.chainxi.common.web.core.databind.LocalDateTimeSerializer;
import com.chainxi.common.web.core.databind.NumberSerializer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import de.codecentric.boot.admin.server.domain.values.Registration;
import de.codecentric.boot.admin.server.utils.jackson.RegistrationDeserializer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Slf4j
public class JsonUtils {
    public static ObjectMapper httpObjectMapper() {
        return new ObjectMapper()
                .registerModules(new SimpleModule()
                                .addSerializer(Registration.class, ToStringSerializer.instance)
                                .addDeserializer(Registration.class, new RegistrationDeserializer())
                                .addSerializer(Long.class, NumberSerializer.INSTANCE)
                                .addSerializer(Long.TYPE, NumberSerializer.INSTANCE),
                        new JavaTimeModule()
                                .addSerializer(LocalDate.class, LocalDateSerializer.INSTANCE)
                                .addDeserializer(LocalDate.class, LocalDateDeserializer.INSTANCE)
                                .addSerializer(LocalTime.class, LocalTimeSerializer.INSTANCE)
                                .addDeserializer(LocalTime.class, LocalTimeDeserializer.INSTANCE)
                                // 新增 LocalDateTime 序列化、反序列化规则
                                .addSerializer(LocalDateTime.class,
                                        LocalDateTimeSerializer.INSTANCE)
                                .addDeserializer(LocalDateTime.class,
                                        LocalDateTimeDeserializer.INSTANCE))
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private static final ObjectMapper mapper = httpObjectMapper();

    @SneakyThrows
    public static String toJSONString(Object object) {
        return mapper.writeValueAsString(object);
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        if (StrUtil.isEmpty(text)) {
            return null;
        }
        try {
            return mapper.readValue(text, clazz);
        } catch (IOException e) {
            log.error("json parse err,json:{}", text, e);
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public static byte[] toJsonByte(Object object) {
        return mapper.writeValueAsBytes(object);
    }

    @SneakyThrows
    public static String toJSONPretty(Object object) {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }

    public static boolean isJson(String text) {
        return JSONUtil.isTypeJSON(text);
    }
}
