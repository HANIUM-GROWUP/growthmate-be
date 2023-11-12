package com.growup.growthmate.batch.comparison;

import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.company.domain.CompanyComparison;
import com.growup.growthmate.fixture.CompanyComparisonFixtureRepository;
import com.growup.growthmate.fixture.CompanyFixtureRepository;
import com.growup.growthmate.isolation.TestIsolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@TestIsolation
class CompanyComparisonProcessorTest {

    @Autowired
    private CompanyComparisonProcessor companyComparisonProcessor;

    @Autowired
    private CompanyFixtureRepository companyFixtureRepository;

    @Autowired
    private CompanyComparisonFixtureRepository companyComparisonFixtureRepository;

    private static final String EXISTS_NAME = "있는 회사";
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
        CompanyComparisonDto comparison = new CompanyComparisonDto(
                "없는 회사", 0.3, 293293412L
        );

        // when
        CompanyComparison actual = companyComparisonProcessor.process(comparison);

        // then
        assertThat(actual).isNull();
    }

    @Test
    void DB에_없는_비교이면_Id_없이_반환한다() {
        // given
        CompanyComparisonDto comparison = new CompanyComparisonDto(
                EXISTS_NAME, 0.3, 293293412L
        );

        // when
        CompanyComparison actual = companyComparisonProcessor.process(comparison);

        // then
        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.getId()).isNull()
        );
    }

    @Test
    void DB에_이미_존재하는_비교면_Id를_넣어_반환한다() {
        // given
        companyComparisonFixtureRepository.save(new CompanyComparison(
                1L, 23423412L, 0.2
        ));
        CompanyComparisonDto comparison = new CompanyComparisonDto(
                EXISTS_NAME, 0.3, 293293412L
        );

        // when
        CompanyComparison actual = companyComparisonProcessor.process(comparison);

        // then
        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.getId()).isNotNull()
        );
    }
}
