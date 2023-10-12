package com.growup.growthmate.batch.analysis;

import com.growup.growthmate.support.XSSSheetUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CompanyAnalysisReader implements ItemReader<CompanyAnalysisDto> {

    private static final int INITIAL_ROW_NUM = 1;

    private final XSSFSheet sheet;
    private final XSSRowToCompanyAnalysisMapper mapper;
    private int rowNum;

    public CompanyAnalysisReader(@Value("${file-path.company-analysis}") String path,
                                 XSSRowToCompanyAnalysisMapper mapper) {
        this.sheet = XSSSheetUtils.initialize(path);
        this.mapper = mapper;
        rowNum = INITIAL_ROW_NUM;
    }

    @Override
    public CompanyAnalysisDto read() {
        return Optional.ofNullable(sheet.getRow(rowNum++))
                .map(mapper::map)
                .orElse(null);
    }
}
