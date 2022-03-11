package com.team.infrastructure.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.team.infrastructure.util.DateTimeUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> implements ContextualDeserializer {

    public static LocalDateTimeDeserializer INSTANCE = new LocalDateTimeDeserializer();

    private DateTimeFormatter formatter;

    public LocalDateTimeDeserializer() {
    }

    public LocalDateTimeDeserializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (formatter != null) {
            String value = p.getValueAsString();
            return StringUtils.isEmpty(value) ? null : LocalDateTime.parse(value, formatter);
        }
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(p.getLongValue());
        return DateTimeUtil.toLocalDateTime(p.getLongValue());
    }


    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        if (property != null) {
            JsonFormat.Value format = ctxt.getAnnotationIntrospector().findFormat(property.getMember());
            if (format != null) {
                if (format.hasPattern()) {
                    return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(format.getPattern()));
                }
            }
        }
        return this;
    }
}