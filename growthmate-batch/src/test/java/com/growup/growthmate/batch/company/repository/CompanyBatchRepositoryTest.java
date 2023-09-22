package com.growup.growthmate.batch.company.repository;

import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.isolation.TestIsolation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestIsolation
class CompanyBatchRepositoryTest {

    @Autowired
    private CompanyBatchRepository companyBatchRepository;

    @PersistenceContext
    private EntityManager em;

    private final List<Company> insertCompanies = List.of(
            new Company(
                    "회사1", "picture.com", "대표", "대기업",
                    "게임", "소프트웨어 공급", LocalDateTime.now(),
                    139485530000L, 43L, "서울"
            ),
            new Company(
                    "회사2", "picture.com", "대표", "대기업",
                    "게임", "소프트웨어 공급", LocalDateTime.now(),
                    139485530000L, 43L, "경기도"
            ),
            new Company(
                    "회사3", "picture.com", "대표", "대기업",
                    "게임", "소프트웨어 공급", LocalDateTime.now(),
                    139485530000L, 43L, "부산"
            )
    );

    @Test
    void 배치_INSERT() {
        // when
        companyBatchRepository.insertAll(insertCompanies);

        // then
        assertAll(
                () -> assertThat(em.find(Company.class, 1L)).isNotNull(),
                () -> assertThat(em.find(Company.class, 2L)).isNotNull(),
                () -> assertThat(em.find(Company.class, 3L)).isNotNull()
        );
    }

    @Test
    void 배치_Update() {
        // given
        companyBatchRepository.insertAll(insertCompanies);
        List<Company> companies = List.of(
                new Company(
                        "회사11", "picture.com", "대표", "대기업",
                        "게임", "소프트웨어 공급", LocalDateTime.now(),
                        139485530000L, 43L, "서울"
                ).withId(1L),
                new Company(
                        "회사22", "picture.com", "대표", "대기업",
                        "게임", "소프트웨어 공급", LocalDateTime.now(),
                        139485530000L, 43L, "경기도"
                ).withId(2L),
                new Company(
                        "회사33", "picture.com", "대표", "대기업",
                        "게임", "소프트웨어 공급", LocalDateTime.now(),
                        139485530000L, 43L, "부산"
                ).withId(3L)
        );

        // when
        companyBatchRepository.updateAll(companies);

        // then
        assertAll(
                () -> assertThat(em.find(Company.class, 1L).getName()).isEqualTo("회사11"),
                () -> assertThat(em.find(Company.class, 2L).getName()).isEqualTo("회사22"),
                () -> assertThat(em.find(Company.class, 3L).getName()).isEqualTo("회사33")
        );
    }
}
