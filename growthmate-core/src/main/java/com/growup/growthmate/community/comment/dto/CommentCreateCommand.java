package com.growup.growthmate.community.comment.dto;

public record CommentCreateCommand(Long postId, String content, Long writerId) {
}
