package com.growup.growthmate.batch.company;

import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.support.exel.XSSFRowMapper;
import com.growup.growthmate.support.exel.XSSFRowUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.stereotype.Component;

@Component
public class XSSFRowToCompanyMapper implements XSSFRowMapper<Company> {

    private static final int NAME = 1;
    private static final int IMAGE = 0;
    private static final int CEO = 2;
    private static final int SCALE = 3;
    private static final int BUSINESS_TYPE = 4;
    private static final int BUSINESS = 5;
    private static final int ESTABLISHMENT_DATE = 6;
    private static final int SALES = 7;
    private static final int EMPLOYEE_NUMBER = 8;
    private static final int ADDRESS = 9;

    @Override
    public Company map(XSSFRow row) {
        return new Company(
                XSSFRowUtils.toStringValue(row, NAME),
                XSSFRowUtils.toStringValue(row, IMAGE),
                XSSFRowUtils.toStringValue(row, CEO),
                XSSFRowUtils.toStringValue(row, SCALE),
                XSSFRowUtils.toStringValue(row, BUSINESS_TYPE),
                XSSFRowUtils.toStringValue(row, BUSINESS),
                XSSFRowUtils.toLocalDateTimeValue(row, ESTABLISHMENT_DATE),
                XSSFRowUtils.toLongValue(row, SALES),
                XSSFRowUtils.toLongValue(row, EMPLOYEE_NUMBER),
                XSSFRowUtils.toStringValue(row, ADDRESS)
        );
    }
}
