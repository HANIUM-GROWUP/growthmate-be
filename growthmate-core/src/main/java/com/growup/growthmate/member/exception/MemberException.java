package com.growup.growthmate.member.exception;

import lombok.Getter;

@Getter
public enum MemberException {

    INVALID_NAME(400, "이름은 공백이면 안됩니다."),
    NO_FOUND_NAME(404, "존재하지 않는 회원입니다."),
    MEMBER_DUPLICATE_EMAIL(400, "이미 등록된 이메일입니다.")

    ;

    private final int httpStatusCode;
    private final String message;

    MemberException(int httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }
}
