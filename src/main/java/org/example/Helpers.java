package org.example;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Helpers {
    public static String formatDateToISO8601(Date date) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .withZone(ZoneId.of("UTC"))
                .format(date.toInstant());
    }
}
