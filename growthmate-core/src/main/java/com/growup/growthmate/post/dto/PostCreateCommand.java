package com.growup.growthmate.post.dto;

public record PostCreateCommand(Long companyId, Long writerId, String title, String content) {
}
