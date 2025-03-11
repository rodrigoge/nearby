package com.api.nearby.account_service.utils;

import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Utils {

    public static String formatDateToResponse(OffsetDateTime offsetDateTime) {
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return offsetDateTime.format(formatter);
    }
}
