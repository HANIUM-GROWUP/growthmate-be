package com.growup.growthmate.batch.sentiment;

import com.growup.growthmate.support.XSSFRowMapper;
import com.growup.growthmate.support.XSSFRowUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.stereotype.Component;

@Component
public class XSSRowToCompanySentimentMapper implements XSSFRowMapper<CompanySentimentDto> {

    private static final int NAME = 0;
    private static final int POSITIVE_RATE = 1;
    private static final int NEGATIVE_RATE = 2;

    @Override
    public CompanySentimentDto map(XSSFRow row) {
        return new CompanySentimentDto(
                XSSFRowUtils.toStringValue(row, NAME),
                XSSFRowUtils.toDoubleValue(row, POSITIVE_RATE),
                XSSFRowUtils.toDoubleValue(row, NEGATIVE_RATE)
        );
    }
}
