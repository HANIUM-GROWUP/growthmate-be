package com.growup.growthmate.support.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class TimeLogAspect {

    @Around("@annotation(com.growup.growthmate.support.log.ExecutionTimeLog)")
    public Object execute(ProceedingJoinPoint joinPoint) {
        try {
            return proceedWithTimeCheck(joinPoint);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private Object proceedWithTimeCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        logExecutionTime(joinPoint, stopWatch.getTotalTimeMillis());
        return result;
    }

    private void logExecutionTime(ProceedingJoinPoint joinPoint, double executionTimeMillis) {
        String targetName = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        log.info("{}.{} 실행 시간 : {}ms", targetName, methodName, executionTimeMillis);
    }
}
