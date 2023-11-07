package com.growup.growthmate.support.exel;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class XSSSheetUtils {

    private static final String MESSAGE = " 엑셀 파일을 찾을 수 없습니다.";

    public static XSSFSheet initialize(String path) throws IOException {
        try (FileInputStream file = new FileInputStream(path)) {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            return workbook.getSheetAt(0);
        } catch (IOException e) {
            log.warn(path + MESSAGE);
            throw e;
        }
    }
}
