package com.growup.growthmate.post.dto;

public record PostUpdateCommand(Long postId, Long writerId, String title, String content) {
}
