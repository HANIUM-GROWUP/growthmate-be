package com.growup.growthmate.company.domain.exception;

import lombok.Getter;

@Getter
public enum CompanyException {

    NO_FOUND_COMPANY (404, "존재하지 않는 회사입니다.")

    ;

    private final int httpStatusCode;
    private final String message;

    CompanyException(int httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }

}
