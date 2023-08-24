package com.growup.growthmate.post.application;

import com.growup.growthmate.post.domain.Post;
import com.growup.growthmate.post.dto.PostCreateCommand;
import com.growup.growthmate.isolation.TestIsolation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@TestIsolation
class PostServiceTest {

    @Autowired
    private PostService postService;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void 게시글을_생성한다() {
        // given
        String title = "안녕하세요";
        String content = "이 회사의 전망이 어떻게 될까요?";
        PostCreateCommand command = new PostCreateCommand(
                1L, 1L, title, content
        );

        // when
        Long postId = postService.create(command);

        // then
        Post post = entityManager.find(Post.class, postId);
        assertAll(
                () -> assertThat(post.getId()).isEqualTo(1L),
                () -> assertThat(post.getTitle().getValue()).isEqualTo(title),
                () -> assertThat(post.getContent().getValue()).isEqualTo(content)
        );
    }
}
