package com.growup.growthmate.batch.company;

import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.fixture.CompanyFixtureRepository;
import com.growup.growthmate.isolation.TestIsolation;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@TestIsolation
@Sql(scripts = "/company-fixture.sql")
class CompanyWriterTest {

    @Autowired
    private CompanyWriter companyWriter;

    @Autowired
    private CompanyFixtureRepository companyFixtureRepository;

    private final List<Company> insertCompanies = List.of(
            new Company(
                    "회사1", "picture.com", "대표", "대기업",
                    "게임", "소프트웨어 공급", null,
                    139485530000L, 43L, "서울"
            ),
            new Company(
                    "회사2", "picture.com", "대표", "대기업",
                    "게임", "소프트웨어 공급", LocalDateTime.now(),
                    139485530000L, 43L, "경기도"
            )
    );


    @Test
    void DB에_없는_회사는_INSERT() {
        // given
        Chunk<Company> chunk = new Chunk<>(insertCompanies);

        // when
        companyWriter.write(chunk);

        // then
        List<Company> companies = companyFixtureRepository.findAll();
        assertThat(companies).hasSize(4);
    }

    @Test
    void DB에_있는_회사는_UPDATE() {
        // given
        AtomicReference<Long> existId = new AtomicReference<>(1L);
        List<Company> updateCompanies = insertCompanies.stream()
                .map(it -> it.withId(existId.getAndSet(existId.get() + 1)))
                .toList();
        Chunk<Company> chunk = new Chunk<>(updateCompanies);

        // when
        companyWriter.write(chunk);

        // then
        List<Company> companies = companyFixtureRepository.findAll();
        assertAll(
                () -> assertThat(companies).hasSize(2),
                () -> assertThat(companies)
                        .map(Company::getName)
                        .containsOnly("회사1", "회사2")
        );
    }
}
