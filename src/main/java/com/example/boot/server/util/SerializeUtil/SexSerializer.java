package com.example.boot.server.util.SerializeUtil;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class SexSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String sex, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        String result;
        if ("1".equals(sex)) {
            result = "男";
        } else if ("2".equals(sex)){
            result = "女";
        } else {
            result = "";
        }
        jsonGenerator.writeString(result);
    }
}
