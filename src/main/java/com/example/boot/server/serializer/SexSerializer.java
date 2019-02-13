package com.example.boot.server.serializer;

import com.example.boot.server.enums.SexEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author LiaoWei
 */
public class SexSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String sex, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (SexEnum.MALE.getKey().equals(sex)) {
            jsonGenerator.writeString(SexEnum.MALE.getValue());
        } else if (SexEnum.FEMALE.getKey().equals(sex)) {
            jsonGenerator.writeString(SexEnum.FEMALE.getValue());
        } else {
            jsonGenerator.writeString("");
        }

    }
}
