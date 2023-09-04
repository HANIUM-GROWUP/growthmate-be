package com.growup.growthmate.community.post.exception;

import com.growup.growthmate.community.CommunityException;
import lombok.Getter;

@Getter
public enum PostException implements CommunityException {

    INVALID_TITLE(400, "게시글 제목은 공백이면 안됩니다."),
    INVALID_CONTENT(400, "게시글 본문은 공백이면 안됩니다."),
    NOT_FOUND_POST(404, "존재하지 않는 게시글입니다.")

    ;

    private final int httpStatusCode;
    private final String message;

    PostException(int httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = message;
    }
}
