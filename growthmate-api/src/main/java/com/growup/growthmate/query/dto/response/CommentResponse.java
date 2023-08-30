package com.growup.growthmate.query.dto.response;

public record CommentResponse(
        Long commentId,
        String writer,
        String content,
        Boolean isMine
) {
}
