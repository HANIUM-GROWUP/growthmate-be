package com.growup.growthmate.post.domain;

import com.growup.growthmate.post.domain.value.CompanyId;
import com.growup.growthmate.post.domain.value.PostContent;
import com.growup.growthmate.post.domain.value.Title;
import com.growup.growthmate.post.domain.value.WriterId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PostTest {

    @Test
    void 작성자가_같으면_True() {
        // given
        Post post = new Post(
                new WriterId(1L),
                new CompanyId(1L),
                new Title("제목"),
                new PostContent("내용")
        );

        // when
        boolean actual = post.isSameWriterId(new WriterId(1L));

        // then
        assertThat(actual).isTrue();
    }

    @Test
    void 작성자가_다르면_False() {
        // given
        Post post = new Post(
                new WriterId(1L),
                new CompanyId(1L),
                new Title("제목"),
                new PostContent("내용")
        );

        // when
        boolean actual = post.isSameWriterId(new WriterId(2L));

        // then
        assertThat(actual).isFalse();
    }
}
