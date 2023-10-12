package com.growup.growthmate.batch.analysis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CompanyAnalysisReaderTest {

    @Autowired
    private CompanyAnalysisReader companyAnalysisReader;

    @Test
    void xlsx파일에서_CompanyAnalysis를_전부_읽는다() {
        // given
        List<CompanyAnalysisDto> analyses = new ArrayList<>();

        // when
        while (true) {
            CompanyAnalysisDto companyAnalysis = companyAnalysisReader.read();
            if (companyAnalysis == null) {
                break;
            }
            analyses.add(companyAnalysis);
        }

        // then
        assertThat(analyses).hasSize(379);
    }

}