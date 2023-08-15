package com.growup.growthmate.comment.exception;

import lombok.Getter;

@Getter
public enum CommentException {

    INVALID_CONTENT(400, "댓글 내용은 공백이면 안됩니다."),
    NOT_FOUND_COMMENT(404, "존재하지 않는 댓글입니다."),
    UNAUTHORIZED_WRITER(403, "댓글에 접근할 권한이 없습니다.")

    ;

    private final int httpStatusCode;
    private final String message;

    CommentException(int httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }
}
