package com.growup.growthmate.batch.analysis;

import com.growup.growthmate.company.domain.CompanyAnalysis;
import com.growup.growthmate.fixture.CompanyAnalysisFixtureRepository;
import com.growup.growthmate.isolation.TestIsolation;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@TestIsolation
@Sql(scripts = "/company-fixture.sql")
class CompanyAnalysisWriterTest {

    @Autowired
    private CompanyAnalysisWriter companyAnalysisWriter;

    @Autowired
    private CompanyAnalysisFixtureRepository companyAnalysisFixtureRepository;

    @Test
    void DB에_없는_분석은_INSERT() {
        // given
        List<CompanyAnalysis> insertAnalyses = List.of(
                new CompanyAnalysis(
                        3L, 50, 60, 70, 80, 90
                ),
                new CompanyAnalysis(
                        4L, 90, 80, 70, 60, 50
                )
        );
        Chunk<CompanyAnalysis> chunk = new Chunk<>(insertAnalyses);

        // when
        companyAnalysisWriter.write(chunk);

        // then
        List<CompanyAnalysis> companies = companyAnalysisFixtureRepository.findAll();
        assertThat(companies).hasSize(4);
    }

    @Test
    void DB에_있는_분석은_UPDATE() {
        // given
        List<CompanyAnalysis> updateAnalyses = List.of(
                new CompanyAnalysis(
                        1L, 1L, 100, 60, 70, 80, 90
                ),
                new CompanyAnalysis(
                        2L, 2L, 100, 80, 70, 60, 50
                )
        );
        Chunk<CompanyAnalysis> chunk = new Chunk<>(updateAnalyses);

        // when
        companyAnalysisWriter.write(chunk);

        // then
        List<CompanyAnalysis> analyses = companyAnalysisFixtureRepository.findAll();
        assertAll(
                () -> assertThat(analyses).hasSize(2),
                () -> assertThat(analyses)
                        .map(CompanyAnalysis::getGrowth)
                        .containsExactly(100, 100)
        );
    }
}
