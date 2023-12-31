package com.growup.growthmate.community.post.domain.value;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.community.post.exception.PostException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TitleTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void 게시글_제목은_공백이면_안된다(String value) {
        // when then
        assertThatThrownBy(() -> new Title(value))
                .isInstanceOf(BusinessException.class)
                .hasMessage(PostException.INVALID_TITLE.getMessage());
    }

    @Test
    void 제목은_null이면_안된다() {
        // when then
        assertThatThrownBy(() -> new Title(null))
                .isInstanceOf(BusinessException.class)
                .hasMessage(PostException.INVALID_TITLE.getMessage());
    }
}
