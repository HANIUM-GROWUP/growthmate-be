package com.growup.growthmate.batch.growth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CompanyGrowthReaderTest {

    @Autowired
    private CompanyGrowthReader companyGrowthReader;

    @Test
    void xlsx파일에서_CompanyGrowth를_전부_읽는다() {
        // given
        List<CompanyGrowthDto> growths = new ArrayList<>();

        // when
        while (true) {
            List<CompanyGrowthDto> growth = companyGrowthReader.read();
            if (growth == null) {
                break;
            }
            growths.addAll(growth);
        }

        // then
        Map<String, List<CompanyGrowthDto>> groupByName = growths.stream()
                .collect(groupingBy(CompanyGrowthDto::name));
        assertThat(groupByName).hasSize(383);
    }
}
