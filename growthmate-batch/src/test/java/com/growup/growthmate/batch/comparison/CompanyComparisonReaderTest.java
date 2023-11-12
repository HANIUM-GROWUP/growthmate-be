package com.growup.growthmate.batch.comparison;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CompanyComparisonReaderTest {

    @Autowired
    private CompanyComparisonReader companyComparisonReader;

    @Test
    void xlsx파일에서_CompanyComparison을_전부_읽는다() {
        // given
        List<CompanyComparisonDto> comparisons = new ArrayList<>();

        // when
        while (true) {
            CompanyComparisonDto companyComparisonDto = companyComparisonReader.read();
            if (companyComparisonDto == null) {
                break;
            }
            comparisons.add(companyComparisonDto);
        }

        // then
        assertThat(comparisons).hasSize(374);
    }
}
