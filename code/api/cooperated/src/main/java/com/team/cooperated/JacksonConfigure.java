package com.team.cooperated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.team.cooperated.json.LocalDateTimeDeserializer;
import com.team.cooperated.json.LocalDateTimeSerializer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDateTime;

@Configuration
public class JacksonConfigure {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder.serializationInclusion(JsonInclude.Include.NON_NULL);
            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer());
            builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer());
            builder.deserializerByType(String.class, stringStdScalarDeserializer());
            builder.featuresToEnable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        };
    }

    public StdScalarDeserializer<String> stringStdScalarDeserializer() {
        return new StdScalarDeserializer<>(String.class) {
            @Override
            public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                return StringUtils.trim(p.getValueAsString());
            }
        };
    }

}
