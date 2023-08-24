package com.growup.growthmate.auth.application;

import com.growup.growthmate.auth.domain.MemberOAuthRepository;
import com.growup.growthmate.auth.dto.AuthorizationRequest;
import com.growup.growthmate.auth.dto.AuthorizationResponse;
import com.growup.growthmate.auth.dto.LoginRequest;
import com.growup.growthmate.auth.dto.LoginResponse;
import com.growup.growthmate.auth.token.JwtTokenProvider;
import com.growup.growthmate.auth.token.TokenPayload;
import com.growup.growthmate.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberOAuthRepository memberRepository;
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public LoginResponse login(LoginRequest request) {
        Long memberId = getOrJoin(request);
        TokenPayload payload = new TokenPayload(memberId);
        String accessToken = jwtTokenProvider.createToken(payload);
        return new LoginResponse(memberId, accessToken);
    }

    private Long getOrJoin(LoginRequest request) {
        return memberRepository.findByEmailAndRegistrationId(request.email(), request.registrationId())
                .orElseGet(() -> memberService.join(request.toMemberRequest()))
                .getId();
    }

    public AuthorizationResponse authorize(AuthorizationRequest request) {
        String accessToken = request.accessToken();
        if (jwtTokenProvider.isValidAccessToken(accessToken)) {
            TokenPayload payload = jwtTokenProvider.getPayload(accessToken);
            return AuthorizationResponse.authorized(payload.id());
        }
        return AuthorizationResponse.unauthorized();
    }

}
