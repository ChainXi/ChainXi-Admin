package com.chainxi.common.web.core.databind;

import cn.hutool.core.util.SerializeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

import java.io.IOException;
import java.security.KeyPair;

/**
 * LocalDateTime序列化规则
 * <p>
 * 会将LocalDateTime序列化为毫秒级时间戳
 */
public class KeyPairSerializer extends JsonSerializer<KeyPair> {

    public static final KeyPairSerializer INSTANCE = new KeyPairSerializer();

    @Override
    public void serialize(KeyPair value,
            JsonGenerator gen,
            SerializerProvider serializers) throws IOException {
        gen.writeBinary(SerializeUtil.serialize(value));
    }

    @Override
    public void serializeWithType(KeyPair value,
            JsonGenerator gen,
            SerializerProvider serializers,
            TypeSerializer typeSer) throws IOException {
        WritableTypeId typeIdDef =
                typeSer.writeTypePrefix(gen, typeSer.typeId(value, JsonToken.VALUE_STRING));
        serialize(value, gen, serializers);
        typeSer.writeTypeSuffix(gen, typeIdDef);
    }


}
