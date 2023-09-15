package com.growup.growthmate.auth.application;

import com.growup.growthmate.auth.domain.MemberOAuthRepository;
import com.growup.growthmate.auth.dto.LoginRequest;
import com.growup.growthmate.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberOAuthRepository memberRepository;
    private final MemberService memberService;

    @Transactional
    public Long login(LoginRequest request) {
        return getOrJoin(request);
    }

    private Long getOrJoin(LoginRequest request) {
        return memberRepository.findByEmailAndRegistrationId(request.email(), request.registrationId())
                .orElseGet(() -> memberService.join(request.toMemberRequest()))
                .getId();
    }
}
