package com.growup.growthmate.community.comment.domain.value;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.community.comment.exception.CommentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommentContentTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void 게시글_내용은_공백이면_안된다(String value) {
        // when then
        assertThatThrownBy(() -> new CommentContent(value))
                .isInstanceOf(BusinessException.class)
                .hasMessage(CommentException.INVALID_CONTENT.getMessage());
    }

    @Test
    void 내용은_null이면_안된다() {
        // when then
        assertThatThrownBy(() -> new CommentContent(null))
                .isInstanceOf(BusinessException.class)
                .hasMessage(CommentException.INVALID_CONTENT.getMessage());
    }
}
