package com.growup.growthmate.comment.dto;

public record CommentUpdateCommand(Long commentId, String content, Long writerId) {
}
