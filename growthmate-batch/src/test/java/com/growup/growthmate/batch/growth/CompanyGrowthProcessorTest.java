package com.growup.growthmate.batch.growth;

import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.company.domain.CompanyGrowth;
import com.growup.growthmate.fixture.CompanyFixtureRepository;
import com.growup.growthmate.fixture.CompanyGrowthFixtureRepository;
import com.growup.growthmate.isolation.TestIsolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@TestIsolation
class CompanyGrowthProcessorTest {

    @Autowired
    private CompanyGrowthProcessor companyGrowthProcessor;

    @Autowired
    private CompanyFixtureRepository companyFixtureRepository;

    @Autowired
    private CompanyGrowthFixtureRepository companyGrowthFixtureRepository;

    private final String existCompanyName = "있는 회사";

    private final Company existCompany = new Company(
            existCompanyName, "picture.com", "대표", "대기업",
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
        String notExistsCompany = "없는 회사";
        List<CompanyGrowthDto> growths = List.of(
                new CompanyGrowthDto(notExistsCompany, 2019, 1029394523.0),
                new CompanyGrowthDto(notExistsCompany, 2020, 1029394523.0),
                new CompanyGrowthDto(notExistsCompany, 2021, 1029394523.0)
        );

        // when
        List<CompanyGrowth> actual = companyGrowthProcessor.process(growths);

        // then
        assertThat(actual).isNull();
    }

    @Test
    void DB에_없는_성장이면_Id_없이_반환한다() {
        // given
        List<CompanyGrowthDto> growths = List.of(
                new CompanyGrowthDto(existCompanyName, 2019, 1029394523.0),
                new CompanyGrowthDto(existCompanyName, 2020, 1029394523.0),
                new CompanyGrowthDto(existCompanyName, 2021, 1029394523.0)
        );

        // when
        List<CompanyGrowth> actual = companyGrowthProcessor.process(growths);

        // then
        assertThat(actual)
                .map(CompanyGrowth::getId)
                .containsOnlyNulls();
    }

    @Test
    void DB에_이미_존재하는_성장이면_Id를_넣어_반환한다() {
        // given
        companyGrowthFixtureRepository.saveAll(List.of(
                new CompanyGrowth(existCompany.getId(), 2019, 1029394523.0),
                new CompanyGrowth(existCompany.getId(), 2020, 1029394523.0),
                new CompanyGrowth(existCompany.getId(), 2021, 1029394523.0)
        ));
        List<CompanyGrowthDto> dtos = List.of(
                new CompanyGrowthDto(existCompanyName, 2019, 1029394521.0),
                new CompanyGrowthDto(existCompanyName, 2020, 1029394522.0),
                new CompanyGrowthDto(existCompanyName, 2021, 1029394523.0),
                new CompanyGrowthDto(existCompanyName, 2022, 1029394524.0)
        );

        // when
        List<CompanyGrowth> actual = companyGrowthProcessor.process(dtos);

        // then
        assertAll(
                () -> assertThat(actual)
                        .map(CompanyGrowth::getId)
                        .containsExactly(1L, 2L, 3L, null),
                () -> assertThat(actual)
                        .map(CompanyGrowth::getYear)
                        .containsExactly(2019, 2020, 2021, 2022),
                () -> assertThat(actual)
                        .map(CompanyGrowth::getSales)
                        .containsExactly(1029394521.0, 1029394522.0, 1029394523.0, 1029394524.0)
        );
    }
}
