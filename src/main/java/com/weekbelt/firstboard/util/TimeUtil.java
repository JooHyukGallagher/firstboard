package com.weekbelt.firstboard.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    public static LocalDateTime convertStringToLocalDateTime(String time){
        return LocalDateTime.parse(time);
    }

    public static String convertLocalDateTimeToString(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
