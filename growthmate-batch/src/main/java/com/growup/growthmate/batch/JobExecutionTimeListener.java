package com.growup.growthmate.batch;

import com.growup.growthmate.support.log.ExecutionTimeLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.util.StopWatch;

import java.util.Collection;

@Slf4j
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
        logStepStatuses(jobExecution);
    }

    private void logStepStatuses(JobExecution jobExecution) {
        StringBuilder stepStatuses = new StringBuilder();
        Collection<StepExecution> stepExecutions = jobExecution.getStepExecutions();
        for (StepExecution stepExecution : stepExecutions) {
            stepStatuses.append(stepExecution.getExitStatus()).append("\n");
        }
        log.info("각 Step 성공 여부 : \n" + stepStatuses);
    }
}
