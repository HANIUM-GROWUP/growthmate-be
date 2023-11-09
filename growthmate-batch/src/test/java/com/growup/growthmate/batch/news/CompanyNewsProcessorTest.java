package com.growup.growthmate.batch.news;

import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.company.domain.CompanyNews;
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
class CompanyNewsProcessorTest {

    @Autowired
    private CompanyNewsProcessor companyNewsProcessor;

    @Autowired
    private CompanyFixtureRepository companyFixtureRepository;

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
        CompanyNewsDto companyNewsDto = new CompanyNewsDto(
                "없는 회사", "기사 제목", "설명", "url.com", "positive"
        );

        // when
        CompanyNews actual = companyNewsProcessor.process(companyNewsDto);

        // then
        assertThat(actual).isNull();
    }

    @Test
    void 있는_회사_이름이면_엔티티를_반환한다() {
        // given
        CompanyNewsDto companyNewsDto = new CompanyNewsDto(
                EXISTS_NAME, "기사 제목", "설명", "url.com", "positive"
        );

        // when
        CompanyNews actual = companyNewsProcessor.process(companyNewsDto);

        // then
        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> {
                    assert actual != null;
                    assertThat(actual.getCompanyId()).isEqualTo(1L);
                }
        );
    }
}
