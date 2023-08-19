package com.growup.growthmate.community.comment.dto;

public record CommentUpdateCommand(Long commentId, String content, Long writerId) {
}
