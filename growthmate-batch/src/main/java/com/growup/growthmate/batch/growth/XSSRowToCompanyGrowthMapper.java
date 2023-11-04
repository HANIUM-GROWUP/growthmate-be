package com.growup.growthmate.batch.growth;

import com.growup.growthmate.support.exel.XSSFRowMapper;
import com.growup.growthmate.support.exel.XSSFRowUtils;
import lombok.Getter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class XSSRowToCompanyGrowthMapper implements XSSFRowMapper<List<CompanyGrowthDto>> {

    private static final int NAME = 0;

    @Override
    public List<CompanyGrowthDto> map(XSSFRow row) {
        String name = XSSFRowUtils.toStringValue(row, NAME);
        return Arrays.stream(RowYear.values())
                .map(rowYear ->
                        new CompanyGrowthDto(
                                name,
                                rowYear.getYear(),
                                XSSFRowUtils.toDoubleValue(row, rowYear.getIndex())
                        )
                ).toList();
    }

    @Getter
    private enum RowYear {
        TWO_THOUSAND_18(1, 2018),
        TWO_THOUSAND_19(2, 2019),
        TWO_THOUSAND_20(3, 2020),
        TWO_THOUSAND_21(4, 2021),
        TWO_THOUSAND_22(5, 2022),
        TWO_THOUSAND_23(6, 2023);

        private final int index;
        private final int year;

        RowYear(int index, int year) {
            this.index = index;
            this.year = year;
        }
    }
}
