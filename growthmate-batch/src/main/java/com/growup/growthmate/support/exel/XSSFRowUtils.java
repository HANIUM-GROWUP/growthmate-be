package com.growup.growthmate.support.exel;

import com.growup.growthmate.support.LocalDateTimeConverter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.time.LocalDateTime;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class XSSFRowUtils {

    public static LocalDateTime toLocalDateTimeValue(XSSFRow row, int cellIndex) {
        return Optional.of(toStringValue(row, cellIndex))
                .filter(LocalDateTimeConverter::isConvertable)
                .map(LocalDateTimeConverter::convert)
                .orElse(null);
    }

    public static String toStringValue(XSSFRow row, int cellIndex) {
        return Optional.ofNullable(row.getCell(cellIndex))
                .map(XSSFRowUtils::extractStringValue)
                .orElse("");
    }

    public static Long toLongValue(XSSFRow row, int cellIndex) {
        return Optional.ofNullable(row.getCell(cellIndex))
                .map(cell -> (long) extractNumericValue(cell))
                .orElse(null);
    }

    public static Integer toIntValue(XSSFRow row, int cellIndex) {
        return Optional.ofNullable(row.getCell(cellIndex))
                .map(cell -> (int) extractNumericValue(cell))
                .orElse(null);
    }

    public static Double toDoubleValue(XSSFRow row, int cellIndex) {
        return Optional.ofNullable(row.getCell(cellIndex))
                .map(XSSFRowUtils::extractNumericValue)
                .orElse(null);
    }

    private static double extractNumericValue(XSSFCell cell) {
        String value = extractStringValue(cell);
        if (value.isBlank()) {
            return 0;
        }
        return Double.parseDouble(value);
    }

    private static String extractStringValue(XSSFCell cell) {
        if (CellType.NUMERIC == cell.getCellType()) {
            return String.valueOf(cell.getNumericCellValue());
        }
        return cell.getStringCellValue();
    }
}
