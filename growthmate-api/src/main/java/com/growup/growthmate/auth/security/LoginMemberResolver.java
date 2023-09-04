package com.growup.growthmate.auth.security;

import com.growup.growthmate.LoginMember;
import com.growup.growthmate.auth.token.JwtSupport;
import com.growup.growthmate.auth.token.JwtTokenProvider;
import com.growup.growthmate.auth.token.TokenPayload;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;
import java.util.Optional;

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
        Optional<String> token = JwtSupport.extractToken(Objects.requireNonNull(request));
        if (token.isPresent()) {
            TokenPayload payload = jwtTokenProvider.getPayload(token.get());
            return new LoginMember(payload.id());
        }
        return LoginMember.NOT_LOGIN;
    }
}
