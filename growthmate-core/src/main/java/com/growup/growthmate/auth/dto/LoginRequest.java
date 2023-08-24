package com.growup.growthmate.auth.dto;

import com.growup.growthmate.member.dto.MemberRequest;

public record LoginRequest(String registrationId, String name, String email, String pictureUrl) {

    public MemberRequest toMemberRequest() {
        return new MemberRequest(registrationId, name, email, pictureUrl);
    }
}
