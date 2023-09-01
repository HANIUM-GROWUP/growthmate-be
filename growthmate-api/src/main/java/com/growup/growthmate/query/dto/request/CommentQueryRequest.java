package com.growup.growthmate.query.dto.request;

public record CommentQueryRequest(Long postId, Long loginId, Long cursor, Integer size) {
}
