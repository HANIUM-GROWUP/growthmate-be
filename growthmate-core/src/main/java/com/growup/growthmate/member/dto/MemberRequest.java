package com.growup.growthmate.member.dto;

import com.growup.growthmate.member.domain.Member;

public record MemberRequest(String registrationId, String name, String email, String pictureUrl) {
    public Member toEntity() {
        return new Member(name, email, pictureUrl, registrationId);
    }
}
