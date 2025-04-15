package com.chainxi.common.web.core.databind;

import cn.hutool.core.util.SerializeUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.security.KeyPair;

public class KeyPairDeserializer extends JsonDeserializer<KeyPair> {

    public static final KeyPairDeserializer INSTANCE = new KeyPairDeserializer();

    @Override
    public KeyPair deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        return SerializeUtil.deserialize(p.getBinaryValue(), byte[].class);
    }


}
