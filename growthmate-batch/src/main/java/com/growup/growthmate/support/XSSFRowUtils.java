package com.growup.growthmate.support;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class XSSFRowUtils {

    private static final Pattern DATE_PATTERN = Pattern.compile("[0-9]+\\s*\\.\\s*[0-9]+\\s*\\.\\s*[0-9]+");

    public static LocalDateTime toLocalDateTimeValue(XSSFRow row, int cellIndex) {
        return Optional.ofNullable(toStringValue(row, cellIndex))
                .filter(stringValue -> DATE_PATTERN.matcher(stringValue).matches())
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
        return Optional.ofNullable(row.getCell(cellIndex))
                .map(XSSFRowUtils::extractString)
                .orElse(null);
    }

    private static String extractString(XSSFCell cell) {
        try {
            return cell.getStringCellValue();
        } catch (IllegalStateException | NumberFormatException e) {
            log.error(cell + "는 StringValue로 변환될 수 없습니다.");
            throw e;
        }
    }

    public static Long toLongValue(XSSFRow row, int cellIndex) {
        return Optional.ofNullable(row.getCell(cellIndex))
                .map(cell -> (long) extractNumeric(cell))
                .orElse(null);
    }

    public static Integer toIntValue(XSSFRow row, int cellIndex) {
        return Optional.ofNullable(row.getCell(cellIndex))
                .map(cell -> (int) extractNumeric(cell))
                .orElse(null);
    }

    private static double extractNumeric(XSSFCell cell) {
        try {
            return cell.getNumericCellValue();
        } catch (IllegalStateException | NumberFormatException e) {
            log.error(cell + "는 NumericValue로 변환될 수 없습니다.");
            throw e;
        }
    }
}
