package com.growup.growthmate;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final int httpStatusCode;

    public BusinessException(int httpStatusCode, String message) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }
}
