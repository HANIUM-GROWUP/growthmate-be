package com.growup.growthmate.support.exel;

import com.growup.growthmate.support.ExelNotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class XSSSheetUtils {

    public static XSSFSheet initialize(String path) {
        try (FileInputStream file = new FileInputStream(path)) {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            return workbook.getSheetAt(0);
        } catch (IOException e) {
            throw new ExelNotFoundException(path);
        }
    }
}
