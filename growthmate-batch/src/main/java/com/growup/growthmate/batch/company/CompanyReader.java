package com.growup.growthmate.batch.company;

import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.support.XSSSheetUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class CompanyReader implements ItemReader<Company> {
    private static final int INITIAL_ROW_NUM = 1;

    private final XSSFSheet sheet;
    private final XSSFRowToCompanyMapper mapper;
    private int rowNum;

    public CompanyReader(@Value("${file-path.company}") String path, XSSFRowToCompanyMapper mapper) {
        this.sheet = XSSSheetUtils.initialize(path);
        this.mapper = mapper;
        rowNum = INITIAL_ROW_NUM;
    }

    @Override
    public Company read() {
        return Optional.ofNullable(sheet.getRow(rowNum++))
                .map(mapper::map)
                .orElse(null);
    }
}
