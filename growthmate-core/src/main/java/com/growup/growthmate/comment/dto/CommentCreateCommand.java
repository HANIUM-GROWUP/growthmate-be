package com.growup.growthmate.comment.dto;

public record CommentCreateCommand(Long postId, String content, Long writerId) {
}
