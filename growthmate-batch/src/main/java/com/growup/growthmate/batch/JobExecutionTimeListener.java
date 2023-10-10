package com.growup.growthmate.batch;

import com.growup.growthmate.support.log.ExecutionTimeLogger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.util.StopWatch;

public class JobExecutionTimeListener implements JobExecutionListener {

    private StopWatch stopWatch;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        stopWatch = new StopWatch();
        stopWatch.start();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        stopWatch.stop();
        ExecutionTimeLogger.log("회사 Batch 전체", stopWatch.getTotalTimeMillis());
    }
}
