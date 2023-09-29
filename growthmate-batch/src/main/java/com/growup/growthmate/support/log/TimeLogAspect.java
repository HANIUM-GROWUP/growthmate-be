package com.growup.growthmate.support.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimeLogAspect {

    @Around("@annotation(com.growup.growthmate.support.log.ExecutionTimeLog)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        Object result = proceedWithTimeCheck(joinPoint, stopWatch);
        ExecutionTimeLogger.log(generateTargetName(joinPoint), stopWatch.getTotalTimeMillis());
        return result;
    }

    private Object proceedWithTimeCheck(ProceedingJoinPoint joinPoint, StopWatch stopWatch) throws Throwable {
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        return result;
    }

    private String generateTargetName(ProceedingJoinPoint joinPoint) {
        String targetClassName = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        return targetClassName + "." + methodName;
    }
}
