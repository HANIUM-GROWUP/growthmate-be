package com.growup.growthmate.comment.exception;

import lombok.Getter;

@Getter
public enum CommentException {

    INVALID_CONTENT(400, "댓글 내용은 공백이면 안됩니다.")

    ;

    private final int httpStatusCode;
    private final String message;

    CommentException(int httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }
}
