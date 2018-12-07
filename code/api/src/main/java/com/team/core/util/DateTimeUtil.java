package com.team.core.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author liugh
 * @since on 2018/5/8.
 */
public class DateTimeUtil {

    /**
     * 私有化构造器，使得不能产生该类对象，类中所有的方法均为静态方法
     */
    private DateTimeUtil() {
    }

    /**
     * 把时间格式化成如：2002-08-03 08:26:16 格式的字符串
     */
    public final static String FMT_yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";

    public static LocalDateTime getCurrentDateTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FMT_yyyyMMddHHmmss);
        return LocalDateTime.parse(dateTimeFormatter.format(LocalDateTime.now()), dateTimeFormatter);
    }
}
