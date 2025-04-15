package com.chainxi.system.config.cache;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.cache.interceptor.SimpleKey;

import java.io.IOException;

public class SimpleKeyDeserializer extends JsonDeserializer<SimpleKey> {

    public static final SimpleKeyDeserializer INSTANCE = new SimpleKeyDeserializer();

    @Override
    public SimpleKey deserialize(JsonParser jsonParser,
            DeserializationContext ctx) throws IOException {
        JsonToken jsonToken = jsonParser.currentToken();
        Object[] elements = null;
        while (!jsonParser.isClosed()) {
            if (JsonToken.FIELD_NAME.equals(jsonToken)) {
                if ("params".equals(jsonParser.currentName())) {
                    jsonParser.nextToken();
                    elements = jsonParser.readValueAs(Object[].class);
                }
            } else if (JsonToken.END_OBJECT.equals(jsonToken)) {
                break;
            }
            jsonToken = jsonParser.nextToken();
        }
        return elements != null ? new SimpleKey(elements) : null;
    }


}
