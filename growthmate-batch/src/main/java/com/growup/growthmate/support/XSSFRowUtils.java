package com.growup.growthmate.support;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.time.LocalDateTime;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class XSSFRowUtils {

    public static Long toLongValue(XSSFRow row, int cellIndex) {
        return findCellByIndex(row, cellIndex)
                .map(cell -> (long) cell.getNumericCellValue())
                .orElse(null);
    }

    public static LocalDateTime toLocalDateTimeValue(XSSFRow row, int cellIndex) {
        return Optional.ofNullable(toStringValue(row, cellIndex))
                .filter(stringValue -> !stringValue.isBlank())
                .map(XSSFRowUtils::convertToLocalDateTime)
                .orElse(null);
    }

    private static LocalDateTime convertToLocalDateTime(String stringValue) {
        String[] dateTime = stringValue.replaceAll(" ", "")
                .split("\\.");
        return LocalDateTime.of(
                Integer.parseInt(dateTime[0]),
                Integer.parseInt(dateTime[1]),
                Integer.parseInt(dateTime[2]),
                0,
                0
        );
    }

    public static String toStringValue(XSSFRow row, int cellIndex) {
        return findCellByIndex(row, cellIndex)
                .map(XSSFCell::getStringCellValue)
                .orElse(null);
    }

    private static Optional<XSSFCell> findCellByIndex(XSSFRow row, int cellIndex) {
        return Optional.ofNullable(row.getCell(cellIndex));
    }
}
