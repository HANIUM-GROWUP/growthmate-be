package com.growup.growthmate.batch.sentiment;

import com.growup.growthmate.company.domain.CompanySentiment;
import com.growup.growthmate.fixture.CompanySentimentFixtureRepository;
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
class CompanySentimentWriterTest {

    @Autowired
    private CompanySentimentWriter companySentimentWriter;

    @Autowired
    private CompanySentimentFixtureRepository companySentimentFixtureRepository;

    @Test
    void DB에_없는_감정은_ISNERT() {
        // given
        List<CompanySentiment> insertSentiments = List.of(
                new CompanySentiment(
                        3L, 0.2, 0.8
                ),
                new CompanySentiment(
                        4L, 0.5, 0.5
                )
        );

        // when
        companySentimentWriter.write(new Chunk<>(insertSentiments));

        // then
        long sentimentCount = companySentimentFixtureRepository.count();
        assertThat(sentimentCount).isEqualTo(4);
    }

    @Test
    void DB에_있는_감정은_UPDATE() {
        // given
        List<CompanySentiment> updateSentiments = List.of(
                new CompanySentiment(
                        1L, 1L, 0.2, 0.8
                ),
                new CompanySentiment(
                        2L, 2L, 0.5, 0.5
                )
        );

        // when
        companySentimentWriter.write(new Chunk<>(updateSentiments));

        // then
        List<CompanySentiment> actual = companySentimentFixtureRepository.findAll();
        assertAll(
                () -> assertThat(actual).hasSize(2),
                () -> assertThat(actual)
                        .filteredOn(sentiment -> sentiment.getCompanyId() == 1L)
                        .map(CompanySentiment::getPositiveRate)
                        .containsExactly(0.2),
                () -> assertThat(actual)
                        .filteredOn(sentiment -> sentiment.getCompanyId() == 1L)
                        .map(CompanySentiment::getNegativeRate)
                        .containsExactly(0.8)
        );
    }
}
