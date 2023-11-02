package com.growup.growthmate.batch.sentiment;

import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.company.domain.CompanySentiment;
import com.growup.growthmate.fixture.CompanyFixtureRepository;
import com.growup.growthmate.fixture.CompanySentimentFixtureRepository;
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
class CompanySentimentProcessorTest {

    @Autowired
    private CompanySentimentProcessor companySentimentProcessor;

    @Autowired
    private CompanyFixtureRepository companyFixtureRepository;

    @Autowired
    private CompanySentimentFixtureRepository companySentimentFixtureRepository;

    private static final String EXISTS_NAME = "있는 회사";
    private final Company existCompany = new Company(
            EXISTS_NAME, "picture.com", "대표", "대기업",
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
        CompanySentimentDto notExistsCompany = new CompanySentimentDto(
                "없는 회사", 0.5, 0.5
        );

        // when
        CompanySentiment actual = companySentimentProcessor.process(notExistsCompany);

        // then
        assertThat(actual).isNull();
    }

    @Test
    void DB에_없는_감정이면_Id_없이_반환한다() {
        // given
        CompanySentimentDto notExistsSentiment = new CompanySentimentDto(
                EXISTS_NAME, 0.5, 0.5
        );

        // when
        CompanySentiment actual = companySentimentProcessor.process(notExistsSentiment);

        // then
        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.getId()).isNull()
        );
    }

    @Test
    void DB에_이미_존재하는_감정이면_Id를_넣어_반환한다() {
        // given
        Long companyId = 1L;
        companySentimentFixtureRepository.save(new CompanySentiment(companyId, 0.5, 0.5));
        CompanySentimentDto existsSentiment = new CompanySentimentDto(
                EXISTS_NAME, 0.8, 0.2
        );

        // when
        CompanySentiment actual = companySentimentProcessor.process(existsSentiment);


        // then
        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.getId()).isNotNull()
        );
    }

}
