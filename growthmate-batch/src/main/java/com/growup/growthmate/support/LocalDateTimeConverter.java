package com.growup.growthmate.support;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalDateTimeConverter {

    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{4}\\.\\d{2}\\.\\d{2}\\.?");

    public static LocalDateTime convert(String value) {
        String[] dateTime = removeBlank(value).split("\\.");
        return LocalDateTime.of(
                Integer.parseInt(dateTime[0]),
                Integer.parseInt(dateTime[1]),
                Integer.parseInt(dateTime[2]),
                0,
                0
        );
    }

    public static boolean isConvertable(String value) {
        return DATE_PATTERN.matcher(removeBlank(value)).matches();
    }

    private static String removeBlank(String stringValue) {
        return stringValue.replaceAll(" ", "");
    }
}
