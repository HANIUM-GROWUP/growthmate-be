package com.growup.growthmate.member.exception;

import lombok.Getter;

@Getter
public enum MemberException {

    INVALID_NAME(400, "이름은 공백이면 안됩니다."),
    NO_FOUND_NAME(404, "존재하지 않는 회원입니다."),
    MEMBER_DUPLICATE_EMAIL(400, "이미 등록된 이메일입니다."),
    AUTHORIZATION_FAIL(400, "인증 실패 회원입니다."),
    NO_FOUND_TOKEN(400, "토큰이 없습니다.")

    ;

    private final int httpStatusCode;
    private final String message;

    MemberException(int httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }
}
