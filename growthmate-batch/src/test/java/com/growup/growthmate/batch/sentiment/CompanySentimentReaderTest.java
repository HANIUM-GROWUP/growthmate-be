package com.growup.growthmate.batch.sentiment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CompanySentimentReaderTest {

    @Autowired
    private CompanySentimentReader companySentimentReader;

    @Test
    void xlsx파일에서_CompanySentiment를_전부_읽는다() {
        // given
        List<CompanySentimentDto> analyses = new ArrayList<>();

        // when
        while (true) {
            CompanySentimentDto companySentiment = companySentimentReader.read();
            if (companySentiment == null) {
                break;
            }
            analyses.add(companySentiment);
        }

        // then
        assertThat(analyses).hasSize(376);
    }
}
