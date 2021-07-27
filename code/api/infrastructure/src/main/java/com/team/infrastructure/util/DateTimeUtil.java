package com.team.infrastructure.util;

import java.time.*;
import java.time.temporal.ChronoUnit;


public class DateTimeUtil {

    private static final ZoneOffset ZONE_OFF_SET = ZoneOffset.ofHours(8);

    public static Long toTimeStamp(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZONE_OFF_SET).toEpochMilli();
    }

    public static LocalDateTime toLocalDateTime(Long timeStamp) {
        String tStr = timeStamp.toString();
        if (tStr.length() == 16) {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp / 1_000).plus(timeStamp % 1_000, ChronoUnit.MICROS), ZoneId.systemDefault());
        }
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp), ZoneId.systemDefault());
    }

    public static Long toTimeStamp(LocalDate localDate) {
        return localDate.atStartOfDay(ZONE_OFF_SET).toInstant().toEpochMilli();
    }

    public static LocalDate toLocalDate(Long timeStamp) {
        return Instant.ofEpochMilli(timeStamp).atZone(ZONE_OFF_SET).toLocalDate();
    }
}
