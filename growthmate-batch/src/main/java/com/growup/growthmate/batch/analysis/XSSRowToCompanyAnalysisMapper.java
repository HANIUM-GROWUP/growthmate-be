package com.growup.growthmate.batch.analysis;

import com.growup.growthmate.support.exel.XSSFRowMapper;
import com.growup.growthmate.support.exel.XSSFRowUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.stereotype.Component;

@Component
public class XSSRowToCompanyAnalysisMapper implements XSSFRowMapper<CompanyAnalysisDto> {

    private static final int NAME = 5;
    private static final int GROWTH = 0;
    private static final int STABILITY = 1;
    private static final int PROFITABILITY = 2;
    private static final int EFFICIENCY = 3;
    private static final int BUSINESS_PERFORMANCE = 4;

    @Override
    public CompanyAnalysisDto map(XSSFRow row) {
        return new CompanyAnalysisDto(
                XSSFRowUtils.toStringValue(row, NAME),
                XSSFRowUtils.toIntValue(row, GROWTH),
                XSSFRowUtils.toIntValue(row, STABILITY),
                XSSFRowUtils.toIntValue(row, PROFITABILITY),
                XSSFRowUtils.toIntValue(row, EFFICIENCY),
                XSSFRowUtils.toIntValue(row, BUSINESS_PERFORMANCE)
        );
    }
}
