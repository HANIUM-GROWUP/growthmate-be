package com.growup.growthmate.support;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class XSSFRowUtils {

    public static long toLongValue(XSSFRow row, int cellIndex) {
        return (long) row.getCell(cellIndex).getNumericCellValue();
    }

    public static LocalDateTime toLocalDateTime(XSSFRow row, int cellIndex) {
        String[] dateTime = toStringValue(row, cellIndex)
                .replaceAll(" ", "")
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
        return row.getCell(cellIndex).getStringCellValue();
    }
}
