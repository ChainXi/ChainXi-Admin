package com.chainxi.system.config.security;

import cn.hutool.core.text.StrPool;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestMappingInfoKeyDeserializer extends KeyDeserializer {
    private static final Pattern PATTERN = Pattern.compile("^\\{\\[(.+?)]? \\[(.+?)].*}");

    public static final RequestMappingInfoKeyDeserializer INSTANCE =
            new RequestMappingInfoKeyDeserializer();

    @Override
    public Object deserializeKey(String key, DeserializationContext ctx) {
        Matcher matcher = PATTERN.matcher(key);
        if (matcher.find()) {
            if (matcher.groupCount() == 2) {
                return RequestMappingInfo
                        .paths(matcher.group(2).split("\\s*,\\s*"))
                        .methods(Arrays
                                .stream(matcher.group(1).split("\\s*,\\s*"))
                                .map(RequestMethod::valueOf)
                                .toArray(RequestMethod[]::new))
                        .build();
            } else {
                return RequestMappingInfo.paths(matcher.group(1).split(StrPool.COMMA)).build();
            }
        }
        return null;
    }
}
