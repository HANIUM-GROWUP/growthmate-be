package com.growup.growthmate.batch.growth;

import com.growup.growthmate.support.XSSSheetUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CompanyGrowthReader implements ItemReader<List<CompanyGrowthDto>> {

    private static final int INITIAL_ROW_NUM = 1;

    private final XSSFSheet sheet;
    private final XSSRowToCompanyGrowthMapper mapper;
    private int rowNum;

    public CompanyGrowthReader(@Value("${file-path.company-growth}") String path, XSSRowToCompanyGrowthMapper mapper) {
        this.sheet = XSSSheetUtils.initialize(path);
        this.mapper = mapper;
        rowNum = INITIAL_ROW_NUM;
    }

    @Override
    public List<CompanyGrowthDto> read() {
        return Optional.ofNullable(sheet.getRow(rowNum++))
                .map(mapper::map)
                .orElse(null);
    }
}
