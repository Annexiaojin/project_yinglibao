package com.xx.micrweb.settings;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
@Configuration
public class JackJsonConfiguration {
    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        /*将null值进行序列化时转化为”“*/
        objectMapper.getSerializerProvider()
                .setNullValueSerializer(new JsonSerializer<Object>() {
                    @Override
                    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                        jsonGenerator.writeString("");
                    }
                });
        return  objectMapper;
    }
}
