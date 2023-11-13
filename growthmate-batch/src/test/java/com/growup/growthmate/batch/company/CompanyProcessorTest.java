package com.growup.growthmate.batch.company;

import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.fixture.CompanyFixtureRepository;
import com.growup.growthmate.isolation.TestIsolation;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestIsolation
class CompanyProcessorTest {

    @Autowired
    private CompanyProcessor companyProcessor;

    @Autowired
    private CompanyFixtureRepository companyFixtureRepository;

    private final Company company = new Company(
            "회사100", "picture.com", "대표", "대기업",
            "게임", "소프트웨어 공급", LocalDateTime.now(),
            139485530000L, 43L, "서울"
    );

    @Test
    @Disabled
    void DB에_없는_회사면_Id_없이_그대로_반환한다() {
        // when
        Company actual = companyProcessor.process(company);

        // then
        assertThat(actual).isEqualTo(company);
    }

    @Test
    void DB에_있는_회사면_Id를_넣어_반환한다() {
        // given
        companyFixtureRepository.save(company);

        // when
        Company actual = companyProcessor.process(company);

        // then
        assert actual != null;
        assertThat(actual.getId()).isNotNull();
    }
}
