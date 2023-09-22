package com.growup.growthmate.batch;

import com.growup.growthmate.isolation.TestIsolation;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SpringBatchTest
@TestIsolation
class CompanyBatchTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    void 회사_배치를_실행한다() throws Exception {
        // when
        JobExecution actual = jobLauncherTestUtils.launchJob();

        // then
        assertThat(actual.getStatus()).isEqualTo(BatchStatus.COMPLETED);
    }
}
