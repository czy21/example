package com.team.cooperated.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.team.infrastructure.util.DateTimeUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Calendar;

public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Calendar c= Calendar.getInstance();
        c.setTimeInMillis(p.getLongValue());
        return DateTimeUtil.toLocalDateTime(p.getLongValue());
    }
}