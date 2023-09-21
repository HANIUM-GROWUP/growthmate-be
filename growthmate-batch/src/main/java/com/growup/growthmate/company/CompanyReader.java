package com.growup.growthmate.company;

import com.growup.growthmate.company.domain.Company;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class CompanyReader implements ItemReader<Company> {
    public static final int INITIAL_ROW_NUM = 1;

    private final XSSFSheet sheet;
    private final XSSFRowToCompanyMapper mapper;
    private int rowNum;

    public CompanyReader(@Value("${company-file-path}") String path, XSSFRowToCompanyMapper mapper) {
        this.sheet = initializeSheet(path);
        this.mapper = mapper;
        rowNum = INITIAL_ROW_NUM;
    }

    private XSSFSheet initializeSheet(String path) {
        try (FileInputStream file = new FileInputStream(path)) {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            return workbook.getSheetAt(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Company read() {
        return Optional.ofNullable(sheet.getRow(rowNum++))
                .map(mapper::map)
                .orElse(null);
    }
}
