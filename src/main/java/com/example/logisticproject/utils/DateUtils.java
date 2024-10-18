package com.example.logisticproject.utils;

import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static DateTimeFormatter getDateFormatter() {
        return DATE_FORMATTER;
    }
    public static DateTimeFormatter getDateTimeFormatter() {
        return DATE_TIME_FORMATTER;
    }
}
