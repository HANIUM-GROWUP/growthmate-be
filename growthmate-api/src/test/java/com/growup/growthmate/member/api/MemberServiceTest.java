package com.growup.growthmate.member.api;

import com.growup.growthmate.auth.dto.LoginRequest;
import com.growup.growthmate.auth.dto.LoginResponse;
import com.growup.growthmate.auth.security.authentication.PrincipalOAuth2UserService;
import com.growup.growthmate.isolation.TestIsolation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@TestIsolation
@Sql(scripts = "/company_fixture.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class MemberServiceTest {

    @Autowired
    private PrincipalOAuth2UserService principalOAuth2UserService;

    @Test
    void authLogin() {

        //given
        LoginRequest request = new LoginRequest("google", "test", "test@email.com", "test.png");

        //when
        LoginResponse response = principalOAuth2UserService.login(request);

        //then
        assertAll(
                () -> assertThat(response.memberId()).isEqualTo(2),
                () -> assertThat(response.accessToken()).isNotNull()
        );
    }
}
