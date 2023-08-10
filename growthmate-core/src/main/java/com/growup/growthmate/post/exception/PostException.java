package com.growup.growthmate.post.exception;

import lombok.Getter;

@Getter
public enum PostException {

    INVALID_TITLE(400, "게시글 제목은 공백이면 안됩니다."),
    INVALID_CONTENT(400, "게시글 본문은 공백이면 안됩니다."),
    NOT_FOUND_POST(404, "존재하지 않는 게시글입니다."),
    UNAUTHORIZED_WRITER(403, "게시글에 접근할 권한이 없습니다.")

    ;

    private final int httpStatusCode;
    private final String message;

    PostException(int httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }
}
