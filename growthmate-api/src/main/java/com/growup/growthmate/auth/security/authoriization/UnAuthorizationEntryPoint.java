package com.growup.growthmate.auth.security.authoriization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.growup.growthmate.member.exception.MemberException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
@RequiredArgsConstructor
public class UnAuthorizationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        int errorCode = MemberException.AUTHORIZATION_FAIL.getHttpStatusCode();
        response.setStatus(errorCode);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");

        PrintWriter writer = response.getWriter();
        writer.println(objectMapper.writeValueAsString(MemberException.AUTHORIZATION_FAIL));
        writer.flush();
    }
}
