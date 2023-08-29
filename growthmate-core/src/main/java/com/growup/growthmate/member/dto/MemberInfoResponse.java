package com.growup.growthmate.member.dto;

import lombok.Builder;

@Builder
public record MemberInfoResponse(String name, String email, String picture) {
}
