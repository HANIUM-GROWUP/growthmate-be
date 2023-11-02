package com.growup.growthmate.batch.news;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CompanyNewsReaderTest {

    @Autowired
    private CompanyNewsReader companyNewsReader;

    @Test
    void xlsx파일에서_CompanySentiment를_전부_읽는다() {
        // given
        List<CompanyNewsDto> news = new ArrayList<>();

        // when
        while (true) {
            CompanyNewsDto companySentiment = companyNewsReader.read();
            if (companySentiment == null) {
                break;
            }
            news.add(companySentiment);
        }

        // then
        assertThat(news).hasSize(55306);
    }
}
