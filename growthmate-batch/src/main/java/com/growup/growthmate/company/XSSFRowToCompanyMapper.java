package com.growup.growthmate.company;

import com.growup.growthmate.XSSFRowMapper;
import com.growup.growthmate.company.domain.Company;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
                row.getCell(NAME).getStringCellValue(),
                row.getCell(IMAGE).getStringCellValue(),
                row.getCell(CEO).getStringCellValue(),
                row.getCell(SCALE).getStringCellValue(),
                row.getCell(BUSINESS_TYPE).getStringCellValue(),
                row.getCell(BUSINESS).getStringCellValue(),
                mapToEstablishmentDate(row),
                Long.parseLong(row.getCell(SALES).getStringCellValue()),
                Long.parseLong(row.getCell(EMPLOYEE_NUMBER).getStringCellValue()),
                row.getCell(ADDRESS).getStringCellValue()
        );
    }

    private static LocalDateTime mapToEstablishmentDate(XSSFRow row) {
        String[] dateTime = row.getCell(ESTABLISHMENT_DATE)
                .getStringCellValue()
                .replaceAll(" ", "")
                .split("\\.");
        return LocalDateTime.of(
                Integer.parseInt(dateTime[0]),
                Integer.parseInt(dateTime[1]),
                Integer.parseInt(dateTime[2]),
                0,
                0
        );
    }
}
