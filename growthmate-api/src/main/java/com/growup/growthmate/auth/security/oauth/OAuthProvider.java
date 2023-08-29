package com.growup.growthmate.auth.security.oauth;

import com.growup.growthmate.auth.dto.LoginRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public enum OAuthProvider {

    GOOGLE {
        @Override
        public LoginRequest toLoginRequest(OAuth2User oAuth2User) {
            return new LoginRequest(
                    name().toLowerCase(),
                    oAuth2User.getAttribute("name"),
                    oAuth2User.getAttribute("email"),
                    oAuth2User.getAttribute("picture")
            );
        }
    }
    ;

    public abstract LoginRequest toLoginRequest(OAuth2User oAuth2User);

    public static OAuthProvider from(String registrationId) {
        return valueOf(registrationId.toUpperCase());
    }
}
