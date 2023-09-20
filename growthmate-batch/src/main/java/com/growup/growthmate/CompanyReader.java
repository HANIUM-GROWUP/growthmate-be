package com.growup.growthmate;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;

@Component
@Slf4j
public class CompanyReader {

    private static final int COLUMN_NUM = 10;

    private final String path;

    public CompanyReader(@Value("${company-file-path}") String path) {
        this.path = path;
    }

    @PostConstruct
    public void read() {
        log.info("path = {}", path);
        try (FileInputStream file = new FileInputStream(path)) {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            for (Row row : workbook.getSheetAt(0)) {
                for (int i = 0; i < COLUMN_NUM; i ++) {
                    printCell(row.getCell(i));
                }
                System.out.println();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void printCell(Cell cell) {
        if (cell == null) {
            return;
        }
        if (cell.getCellType().equals(CellType.STRING)) {
            System.out.print(cell.getStringCellValue() + " | ");
        } else {
            System.out.print(cell.getNumericCellValue() + " | ");
        }
    }
}
