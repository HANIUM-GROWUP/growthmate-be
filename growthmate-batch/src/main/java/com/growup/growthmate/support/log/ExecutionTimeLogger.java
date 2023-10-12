package com.growup.growthmate.support.log;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class ExecutionTimeLogger {

    public static void log(String targetName, double executionTimeMillis) {
        log.info("{} 실행 시간 : {}ms", targetName, executionTimeMillis);
    }
}
