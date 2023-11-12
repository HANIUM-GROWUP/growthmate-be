package com.growup.growthmate.batch.comparison;

import com.growup.growthmate.support.exel.XSSFRowMapper;
import com.growup.growthmate.support.exel.XSSFRowUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.stereotype.Component;

@Component
public class XSSRowToCompanyComparisonMapper implements XSSFRowMapper<CompanyComparisonDto> {

    private static final int NAME = 0;
    private static final int SALES_FORECAST_PERCENTAGE = 1;
    private static final int SALES_FORECAST = 4;
    @Override
    public CompanyComparisonDto map(XSSFRow row) {
        return new CompanyComparisonDto(
                XSSFRowUtils.toStringValue(row, NAME),
                XSSFRowUtils.toDoubleValue(row, SALES_FORECAST_PERCENTAGE),
                XSSFRowUtils.toLongValue(row, SALES_FORECAST)
        );
    }
}
