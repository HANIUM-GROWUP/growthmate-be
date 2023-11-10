package com.growup.growthmate.batch.comparison;

import com.growup.growthmate.company.domain.CompanyComparison;
import com.growup.growthmate.fixture.CompanyComparisonFixtureRepository;
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
class CompanyComparisonWriterTest {

    @Autowired
    private CompanyComparisonWriter companyComparisonWriter;

    @Autowired
    private CompanyComparisonFixtureRepository companyComparisonFixtureRepository;

    @Test
    void DB에_없는_비교는_ISNERT() {
        // given
        List<CompanyComparison> comparisons = List.of(
                new CompanyComparison(3L, 2341235L, 0.2),
                new CompanyComparison(4L, 56453546L, 0.4)
        );

        // when
        companyComparisonWriter.write(new Chunk<>(comparisons));

        // then
        long count = companyComparisonFixtureRepository.count();
        assertThat(count).isEqualTo(4);
    }

    @Test
    void DB에_있는_비교는_UPDATE() {
        // given
        List<CompanyComparison> comparisons = List.of(
                new CompanyComparison(1L, 1L, 2341235L, 0.3),
                new CompanyComparison(2L, 2L, 56453546L, 0.4)
        );

        // when
        companyComparisonWriter.write(new Chunk<>(comparisons));

        // then
        List<CompanyComparison> actual = companyComparisonFixtureRepository.findAll();
        assertAll(
                () -> assertThat(actual).hasSize(2),
                () -> assertThat(actual)
                        .map(CompanyComparison::getSalesForecastPercentage)
                        .containsOnly(0.3, 0.4)
        );
    }
}
