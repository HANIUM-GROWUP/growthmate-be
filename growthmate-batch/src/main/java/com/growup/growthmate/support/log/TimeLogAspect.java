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
        StopWatch stopWatch = new StopWatch();
        Object result;
        try {
            stopWatch.start();
            result = joinPoint.proceed();
            stopWatch.stop();
            log.info("실행 시간 (ms): {}", stopWatch.getTotalTimeMillis());
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
