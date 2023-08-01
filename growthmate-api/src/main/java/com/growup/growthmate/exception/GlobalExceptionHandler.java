package com.growup.growthmate.exception;

import com.growup.growthmate.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleBusinessException(BusinessException exception) {
        return ResponseEntity.status(exception.getHttpStatusCode())
                .body("message : " + exception.getMessage());
    }
}
