package com.growup.growthmate.member.domain;

import com.growup.growthmate.isolation.TestIsolation;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestIsolation
public class MemberNameTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "longerthantenwords"})
    public void testNameLengthValidation(String value) {

        Member member = Member.builder().name(value).build();

        Set<ConstraintViolation<Member>> violations = validator.validate(member);

        // 검증 결과 확인
        assertEquals(1, violations.size()); // 오류가 1개 발견되어야 함

        ConstraintViolation<Member> violation = violations.iterator().next();
        assertEquals("이름은 최소 1자 부터 10자 이하여야 합니다.", violation.getMessage());
    }

}
