package com.growup.growthmate.auth.security;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.LoginMember;
import com.growup.growthmate.auth.token.JwtSupport;
import com.growup.growthmate.auth.token.JwtTokenProvider;
import com.growup.growthmate.auth.token.TokenPayload;
import com.growup.growthmate.member.exception.MemberException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class LoginMemberResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(LoginMember.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String accessToken = getAccessToken(request);
        TokenPayload payload = jwtTokenProvider.getPayload(accessToken);
        return new LoginMember(payload.id());
    }

    private String getAccessToken(HttpServletRequest request) {

        return JwtSupport.extractToken(Objects.requireNonNull(request))
                .orElseThrow();
    }
}
