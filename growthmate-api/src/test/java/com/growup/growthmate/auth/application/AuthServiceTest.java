package com.growup.growthmate.auth.application;

import com.growup.growthmate.GrowthMateApiApplication;
import com.growup.growthmate.auth.dto.LoginRequest;
import com.growup.growthmate.auth.dto.LoginResponse;
import com.growup.growthmate.auth.token.JwtTokenProvider;
import com.growup.growthmate.member.domain.Member;
import com.growup.growthmate.member.domain.MemberRepository;
import io.jsonwebtoken.io.Encoders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final LoginRequest loginRequest = new LoginRequest(
            "google", "안정후", "ajh@mail.com", "picture.com"
    );

    @Test
    void encode() {

        String secretKey = "HanEum GrowthMate Backend Random Encoding Secret Key";

        System.out.println(Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    void 처음인_사용자는_DB에_저장된다() {
        // when˚
        LoginResponse loginResponse = authService.login(loginRequest);

        // then
        Long memberId = loginResponse.memberId();
        Optional<Member> joinedMember = memberRepository.findById(memberId);
        assertAll(
                () -> assertThat(joinedMember).isPresent(),
                () -> assertThat(loginResponse.accessToken()).isNotNull(),
                () -> assertThat(memberRepository.findAll().size()).isOne()
        );
    }

    @DisplayName("같은 OAuth Client로 가입된 사용자는 DB에 또 저장되지 않는다.")
    @Test
    void 같은_OAuth_Client로_가입된_사용자는_DB에_또_저장되지_않는다() {
        // given
        authService.login(loginRequest);

        // when
        LoginResponse loginResponse = authService.login(loginRequest);

        // then
        Long memberId = loginResponse.memberId();
        Optional<Member> joinedMember = memberRepository.findById(memberId);
        assertAll(
                () -> assertThat(joinedMember).isPresent(),
                () -> assertThat(loginResponse.accessToken()).isNotNull(),
                () -> assertThat(memberRepository.findAll().size()).isOne()
        );
    }

}