package com.growup.growthmate.batch.news;

import com.growup.growthmate.support.XSSSheetUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CompanyNewsReader implements ItemReader<CompanyNewsDto> {

    private static final int INITIAL_ROW_NUM = 1;

    private final XSSFSheet sheet;
    private final XSSRowToCompanyNewsMapper mapper;
    private int rowNum;

    public CompanyNewsReader(@Value("${file-path.company-news}") String path,
                             XSSRowToCompanyNewsMapper mapper) {
        this.sheet = XSSSheetUtils.initialize(path);
        this.mapper = mapper;
        rowNum = INITIAL_ROW_NUM;
    }
    @Override
    public CompanyNewsDto read() {
        return Optional.ofNullable(sheet.getRow(rowNum++))
                .map(mapper::map)
                .orElse(null);
    }
}
