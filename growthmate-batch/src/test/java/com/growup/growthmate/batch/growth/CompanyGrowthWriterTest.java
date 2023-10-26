package com.growup.growthmate.batch.growth;

import com.growup.growthmate.company.domain.CompanyGrowth;
import com.growup.growthmate.fixture.CompanyGrowthFixtureRepository;
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
class CompanyGrowthWriterTest {

    @Autowired
    private CompanyGrowthWriter companyGrowthWriter;

    @Autowired
    private CompanyGrowthFixtureRepository companyGrowthFixtureRepository;

    @Test
    void DB에_없는_성장은_INSERT() {
        // given
        List<List<CompanyGrowth>> insertGrowths = List.of(
                List.of(new CompanyGrowth(1L, 2022, 20000.0),
                        new CompanyGrowth(1L, 2023, 20000.0)
                ),
                List.of(
                        new CompanyGrowth(2L, 2022, 20000.0),
                        new CompanyGrowth(2L, 2023, 20000.0)
                )
        );

        // when
        companyGrowthWriter.write(new Chunk<>(insertGrowths));

        // then
        List<CompanyGrowth> actual = companyGrowthFixtureRepository.findAll();
        assertAll(
                () -> assertThat(actual).hasSize(10),
                () -> assertThat(actual)
                        .map(CompanyGrowth::getYear)
                        .containsOnly(2018, 2019, 2020, 2022, 2023)
        );
    }

    @Test
    void DB에_있는_성장은_UPDATE() {
        // given
        List<List<CompanyGrowth>> updateGrowths = List.of(
                List.of(new CompanyGrowth(1L, 1L, 2018, 20000.0),
                        new CompanyGrowth(2L, 1L, 2019, 20000.0)
                ),
                List.of(
                        new CompanyGrowth(4L, 2L, 2018, 20000.0),
                        new CompanyGrowth(5L, 2L, 2019, 20000.0),
                        new CompanyGrowth( 2L, 2023, 10000.0)
                )
        );

        // when
        companyGrowthWriter.write(new Chunk<>(updateGrowths));

        // then
        List<CompanyGrowth> actual = companyGrowthFixtureRepository.findAll();
        List<Long> updatedId = List.of(1L, 2L, 4L, 5L);
        assertAll(
                () -> assertThat(actual).hasSize(7),
                () -> assertThat(actual)
                        .map(CompanyGrowth::getYear)
                        .containsOnly(2018, 2019, 2020, 2023),
                () -> assertThat(actual)
                        .filteredOn(growth -> updatedId.contains(growth.getId()))
                        .map(CompanyGrowth::getSales)
                        .containsOnly(20000.0),
                () -> assertThat(actual)
                        .filteredOn(growth -> !updatedId.contains(growth.getId()))
                        .map(CompanyGrowth::getSales)
                        .containsOnly(10000.0)
        );
    }
}
