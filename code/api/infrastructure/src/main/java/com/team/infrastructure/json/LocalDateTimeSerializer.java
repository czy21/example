package com.team.infrastructure.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.team.infrastructure.util.DateTimeUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> implements ContextualSerializer {

    public static LocalDateTimeSerializer INSTANCE = new LocalDateTimeSerializer();

    private DateTimeFormatter formatter;

    public LocalDateTimeSerializer() {
    }

    public LocalDateTimeSerializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (formatter != null) {
            gen.writeString(value.format(formatter));
        } else {
            gen.writeNumber(DateTimeUtil.toTimeStamp(value));
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
        if (property != null) {
            JsonFormat.Value format = prov.getAnnotationIntrospector().findFormat(property.getMember());
            if (format != null) {
                if (format.hasPattern()) {
                    return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(format.getPattern()));
                }
            }
        }
        return this;
    }
}