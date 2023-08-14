package com.growup.growthmate.auth.security.authentication;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.auth.application.AuthService;
import com.growup.growthmate.auth.dto.LoginRequest;
import com.growup.growthmate.auth.dto.LoginResponse;
import com.growup.growthmate.auth.security.oauth.OAuth2Member;
import com.growup.growthmate.auth.security.oauth.OAuthProvider;
import com.growup.growthmate.member.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {

    private final AuthService authService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        LoginRequest loginRequest = toLoginRequest(userRequest);
        LoginResponse loginResponse = login(loginRequest);
        return new OAuth2Member(
                loginResponse.memberId(),
                loginResponse.accessToken()
        );
    }

    private LoginRequest toLoginRequest(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuthProvider oAuthProvider = OAuthProvider.from(userRequest.getClientRegistration().getRegistrationId());
        return oAuthProvider.toLoginRequest(oAuth2User);
    }

    private LoginResponse login(LoginRequest loginRequest) {
        try {
            return authService.login(loginRequest);
        } catch (BusinessException exception) {
            OAuth2Error oAuth2Error = new OAuth2Error(String.valueOf(500));
            throw new OAuth2AuthenticationException(oAuth2Error);
        }
    }
}
