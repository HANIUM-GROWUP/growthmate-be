package com.growup.growthmate.batch.analysis;

import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.company.domain.CompanyAnalysis;
import com.growup.growthmate.fixture.CompanyAnalysisFixtureRepository;
import com.growup.growthmate.fixture.CompanyFixtureRepository;
import com.growup.growthmate.isolation.TestIsolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestIsolation
class CompanyAnalysisProcessorTest {

    @Autowired
    private CompanyAnalysisProcessor companyAnalysisProcessor;

    @Autowired
    private CompanyFixtureRepository companyFixtureRepository;

    @Autowired
    private CompanyAnalysisFixtureRepository companyAnalysisFixtureRepository;

    private final Company existCompany = new Company(
            "있는 회사", "picture.com", "대표", "대기업",
            "게임", "소프트웨어 공급", LocalDateTime.now(),
            139485530000L, 43L, "서울"
    );

    @BeforeEach
    void init() {
        companyFixtureRepository.save(existCompany);
    }


    @Test
    void 없는_회사_이름이면_null을_반환한다() {
        // given
        CompanyAnalysisDto dto = new CompanyAnalysisDto(
                "없는 회사", 50, 50, 50, 50, 50
        );

        // when
        CompanyAnalysis actual = companyAnalysisProcessor.process(dto);

        // then
        assertThat(actual).isNull();
    }

    @Test
    void DB에_없는_분석이면_Id_없이_반환한다() {
        // given
        CompanyAnalysisDto dto = new CompanyAnalysisDto(
                existCompany.getName(), 50, 50, 50, 50, 50
        );

        // when
        CompanyAnalysis actual = companyAnalysisProcessor.process(dto);

        // then
        assert actual != null;
        assertThat(actual.getId()).isNull();
    }

    @Test
    void DB에_이미_존재하는_분석이면_Id를_넣어_반환한다() {
        // given
        Long companyId = 1L;
        CompanyAnalysis analysis = new CompanyAnalysis(
                companyId, 50, 50, 50, 50, 50
        );
        companyAnalysisFixtureRepository.save(analysis);
        CompanyAnalysisDto dto = new CompanyAnalysisDto(
                existCompany.getName(), 50, 50, 50, 50, 50
        );

        // when
        CompanyAnalysis actual = companyAnalysisProcessor.process(dto);

        // then
        assert actual != null;
        assertThat(actual.getId()).isNotNull();
    }
}
