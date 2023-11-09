package com.growup.growthmate.batch.news;

import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.company.domain.CompanyNews;
import com.growup.growthmate.company.domain.Sentiment;
import com.growup.growthmate.fixture.CompanyFixtureRepository;
import com.growup.growthmate.fixture.CompanyNewsFixtureRepository;
import com.growup.growthmate.isolation.TestIsolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@TestIsolation
class CompanyNewsWriterTest {

    @Autowired
    private CompanyNewsWriter companyNewsWriter;

    @Autowired
    private CompanyFixtureRepository companyFixtureRepository;

    @Autowired
    private CompanyNewsFixtureRepository companyNewsFixtureRepository;

    private static final String EXISTS_NAME = "있는 회사";
    private final Company existCompany = new Company(
            EXISTS_NAME, "picture.com", "대표", "대기업",
            "게임", "소프트웨어 공급", LocalDateTime.now(),
            139485530000L, 43L, "서울"
    );

    private Long companyId;

    @BeforeEach
    void init() {
        companyId = companyFixtureRepository.save(existCompany).getId();
    }

    @Test
    void DB에_없는_감정은_ISNERT() {
        // given
        List<CompanyNews> insertNews = List.of(
                new CompanyNews(
                        companyId, "기사1", "기사 설멍", "url-1.com", Sentiment.POSITIVE
                ),
                new CompanyNews(
                        companyId, "기사2", "기사 설멍", "url-2.com", Sentiment.NEGATIVE
                )
        );

        // when
        companyNewsWriter.write(new Chunk<>(insertNews));

        // then
        List<CompanyNews> actual = companyNewsFixtureRepository.findAll();
        assertAll(
                () -> assertThat(actual).hasSize(2),
                () -> assertThat(actual)
                        .map(CompanyNews::getTitle)
                        .containsOnly("기사1", "기사2"),
                () -> assertThat(actual)
                        .map(CompanyNews::getSentiment)
                        .containsOnly(Sentiment.POSITIVE, Sentiment.NEGATIVE)
        );
    }
}
