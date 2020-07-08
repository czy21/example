package com.team.infrastructure.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


public class DateTimeUtil {

    private static final ZoneOffset ZONE_OFF_SET = ZoneOffset.ofHours(8);

    public static Long toTimeStamp(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZONE_OFF_SET).toEpochMilli();
    }

    public static LocalDateTime toLocalDateTime(Long timeStamp) {
        return Instant.ofEpochMilli(timeStamp).atZone(ZONE_OFF_SET).toLocalDateTime();
    }

    public static Long toTimeStamp(LocalDate localDate) {
        return localDate.atStartOfDay(ZONE_OFF_SET).toInstant().toEpochMilli();
    }

    public static LocalDate toLocalDate(Long timeStamp) {
        return Instant.ofEpochMilli(timeStamp).atZone(ZONE_OFF_SET).toLocalDate();
    }
}
