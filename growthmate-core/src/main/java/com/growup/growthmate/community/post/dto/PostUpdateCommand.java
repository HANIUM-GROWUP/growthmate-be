package com.growup.growthmate.community.post.dto;

public record PostUpdateCommand(Long postId, Long writerId, String title, String content) {
}
