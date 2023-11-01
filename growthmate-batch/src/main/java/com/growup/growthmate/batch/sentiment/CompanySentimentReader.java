package com.growup.growthmate.batch.sentiment;

import com.growup.growthmate.support.XSSSheetUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CompanySentimentReader implements ItemReader<CompanySentimentDto> {

    private static final int INITIAL_ROW_NUM = 1;

    private final XSSFSheet sheet;
    private final XSSRowToCompanySentimentMapper mapper;
    private int rowNum;

    public CompanySentimentReader(@Value("${file-path.company-sentiment}") String path,
                                  XSSRowToCompanySentimentMapper mapper) {
        this.sheet = XSSSheetUtils.initialize(path);
        this.mapper = mapper;
        rowNum = INITIAL_ROW_NUM;
    }

    @Override
    public CompanySentimentDto read() {
        return Optional.ofNullable(sheet.getRow(rowNum++))
                .map(mapper::map)
                .orElse(null);
    }
}
