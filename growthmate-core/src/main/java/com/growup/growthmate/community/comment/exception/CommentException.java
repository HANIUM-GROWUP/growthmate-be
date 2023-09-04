package com.growup.growthmate.community.comment.exception;

import com.growup.growthmate.community.CommunityException;
import lombok.Getter;

@Getter
public enum CommentException implements CommunityException {

    INVALID_CONTENT(400, "댓글 내용은 공백이면 안됩니다."),
    NOT_FOUND_COMMENT(404, "존재하지 않는 댓글입니다."),

    ;

    private final int httpStatusCode;
    private final String message;

    CommentException(int httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }
}
