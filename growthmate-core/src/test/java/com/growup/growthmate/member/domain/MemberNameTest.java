package com.growup.growthmate.member.domain;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.community.post.domain.value.PostContent;
import com.growup.growthmate.community.post.exception.PostException;
import com.growup.growthmate.isolation.TestIsolation;
import com.growup.growthmate.member.exception.MemberException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemberNameTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "longerthantenwords"})
    void 이름은_공백이거나_10자리를_초과하면_안된다 (String value) {
        // when then
        assertThatThrownBy(() -> new Member().updateName(value))
                .isInstanceOf(BusinessException.class)
                .hasMessage(MemberException.INVALID_NAME.getMessage());
    }

}
