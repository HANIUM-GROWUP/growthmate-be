package com.growup.growthmate.batch.news;

import com.growup.growthmate.support.exel.XSSFRowMapper;
import com.growup.growthmate.support.exel.XSSFRowUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.stereotype.Component;

@Component
public class XSSRowToCompanyNewsMapper implements XSSFRowMapper<CompanyNewsDto> {

    private static final int NAME = 0;
    private static final int TITLE = 1;
    private static final int DESCRIPTION = 2;
    private static final int URL = 3;
    private static final int SENTIMENT = 4;

    @Override
    public CompanyNewsDto map(XSSFRow row) {
        return new CompanyNewsDto(
                XSSFRowUtils.toStringValue(row, NAME),
                XSSFRowUtils.toStringValue(row, TITLE),
                XSSFRowUtils.toStringValue(row, DESCRIPTION),
                XSSFRowUtils.toStringValue(row, URL),
                XSSFRowUtils.toStringValue(row, SENTIMENT)
        );
    }
}
