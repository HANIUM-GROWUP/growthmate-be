package com.growup.growthmate.batch;

import com.growup.growthmate.support.log.ExecutionTimeLogger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.util.StopWatch;

public class StepExecutionTimeListener implements StepExecutionListener {

    private StopWatch stopWatch;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        stopWatch = new StopWatch();
        stopWatch.start();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        stopWatch.stop();
        ExecutionTimeLogger.log(stepExecution.getStepName() + "Step", stopWatch.getTotalTimeMillis());
        return ExitStatus.COMPLETED;
    }
}
