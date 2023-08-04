package com.growup.growthmate.post.domain.value;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.post.exception.PostException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PostContentTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void 게시글_내용은_공백이면_안된다(String value) {
        // when then
        assertThatThrownBy(() -> new PostContent(value))
                .isInstanceOf(BusinessException.class)
                .hasMessage(PostException.INVALID_CONTENT.getMessage());
    }
}
