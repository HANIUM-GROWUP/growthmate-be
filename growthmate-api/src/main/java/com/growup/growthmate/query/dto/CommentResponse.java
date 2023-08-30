package com.growup.growthmate.query.dto;

public record CommentResponse(
        Long commentId,
        String writer,
        String content,
        Boolean isMine
) {
}
