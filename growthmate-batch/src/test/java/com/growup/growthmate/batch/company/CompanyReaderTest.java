package com.growup.growthmate.batch.company;

import com.growup.growthmate.company.domain.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CompanyReaderTest {

    @Autowired
    private CompanyReader companyReader;

    @Test
    void xlsx파일에서_Company를_전부_읽는다() {
        // given
        List<Company> companies = new ArrayList<>();

        // when
        while (true) {
            Company company = companyReader.read();
            if (company == null) {
                break;
            }
            companies.add(company);
        }

        // then
        assertThat(companies).hasSize(29);
    }
}
