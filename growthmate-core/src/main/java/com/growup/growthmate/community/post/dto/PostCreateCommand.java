package com.growup.growthmate.community.post.dto;

public record PostCreateCommand(Long companyId, Long writerId, String title, String content) {
}
