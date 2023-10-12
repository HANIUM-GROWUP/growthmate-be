package com.growup.growthmate.batch;

import com.growup.growthmate.fixture.CompanyAnalysisFixtureRepository;
import com.growup.growthmate.fixture.CompanyFixtureRepository;
import com.growup.growthmate.isolation.TestIsolation;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@SpringBatchTest
@TestIsolation
class CompanyBatchTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private CompanyFixtureRepository companyFixtureRepository;

    @Autowired
    private CompanyAnalysisFixtureRepository companyAnalysisFixtureRepository;

    @Test
    void 회사_배치를_실행한다() throws Exception {
        // when
        JobExecution actual = jobLauncherTestUtils.launchJob();

        // then
        long companySize = companyFixtureRepository.count();
        long analysisSize = companyAnalysisFixtureRepository.count();
        assertAll(
                () -> assertThat(actual.getStatus()).isEqualTo(BatchStatus.COMPLETED),
                () -> assertThat(companySize).isEqualTo(355),
                () -> assertThat(analysisSize).isEqualTo(355)
        );

    }
}
