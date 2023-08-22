package com.growup.growthmate.auth.security.authentication;

import com.growup.growthmate.auth.security.oauth.OAuth2Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuthLoginSuccessHandler implements AuthenticationSuccessHandler {

    private static final String SET_COOKIE = "Set-Cookie";
    private static final String AUTHORIZATION = "Authorization";

    @Value("${security.jwt.token.redirect-uri}")
    private String redirectUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2Member oauth2Member = (OAuth2Member) authentication.getPrincipal();

        response.setContentType("application/json;charset=UTF-8");
        response.addHeader(AUTHORIZATION, "Bearer " + oauth2Member.getAccessToken());
        response.sendRedirect(redirectUrl);
    }
}
