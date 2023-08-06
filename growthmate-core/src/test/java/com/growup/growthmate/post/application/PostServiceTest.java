package com.growup.growthmate.post.application;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.post.domain.Post;
import com.growup.growthmate.post.dto.PostCreateCommand;
import com.growup.growthmate.post.dto.PostUpdateCommand;
import com.growup.growthmate.post.exception.PostException;
import com.growup.isolation.TestIsolation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@TestIsolation
class PostServiceTest {

    private static final String TEST_TITLE = "안녕하세요";
    private static final String TEST_CONTENT = "이 회사의 전망이 어떻게 될까요?";
    private static final Long TEST_COMPANY_ID = 1L;
    private static final Long TEST_WRITER_ID = 1L;

    @Autowired
    private PostService postService;

    @PersistenceContext
    private EntityManager entityManager;

    @DisplayName("게시글을 생성할 때")
    @Nested
    class CreateTest {

        @Test
        void 유효한_제목과_내용은_생성한다() {
            // given
            PostCreateCommand command = new PostCreateCommand(
                    TEST_COMPANY_ID, TEST_WRITER_ID, TEST_TITLE, TEST_CONTENT
            );

            // when
            Long postId = postService.create(command);

            // then
            Post post = entityManager.find(Post.class, postId);
            assertAll(
                    () -> assertThat(post.getId()).isEqualTo(1L),
                    () -> assertThat(post.getTitle().getValue()).isEqualTo(TEST_TITLE),
                    () -> assertThat(post.getContent().getValue()).isEqualTo(TEST_CONTENT)
            );
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " "})
        void 제목이_비어_있으면_예외가_발생한다(String blankTitle) {
            // given
            PostCreateCommand command = new PostCreateCommand(
                    TEST_COMPANY_ID, TEST_WRITER_ID, blankTitle, TEST_CONTENT
            );

            // when then
            assertThatThrownBy(() -> postService.create(command))
                    .isInstanceOf(BusinessException.class)
                    .hasMessage(PostException.INVALID_TITLE.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " "})
        void 내용이_비어_있으면_예외가_발생한다(String blankContent) {
            // given
            PostCreateCommand command = new PostCreateCommand(
                    TEST_COMPANY_ID, TEST_WRITER_ID, TEST_TITLE, blankContent
            );

            // when then
            assertThatThrownBy(() -> postService.create(command))
                    .isInstanceOf(BusinessException.class)
                    .hasMessage(PostException.INVALID_CONTENT.getMessage());
        }

    }

    @DisplayName("게시글을 수정할 때")
    @Nested
    class UpdateTest {

        private Long postId;
        private static final String NEW_TITLE = "변경된 제목";
        private static final String NEW_CONTENT = "변경된 내용";

        @BeforeEach
        void initPost() {
            postId = postService.create(new PostCreateCommand(
                    TEST_COMPANY_ID, TEST_WRITER_ID, TEST_TITLE, TEST_CONTENT
            ));
        }

        @Test
        void 유효한_제목과_내용은_수정한다() {
            // given
            PostUpdateCommand command = new PostUpdateCommand(postId, TEST_WRITER_ID, NEW_TITLE, NEW_CONTENT);

            // when
            postService.update(command);

            // then
            Post post = entityManager.find(Post.class, postId);
            assertAll(
                    () -> assertThat(post.getId()).isEqualTo(1L),
                    () -> assertThat(post.getTitle().getValue()).isEqualTo(NEW_TITLE),
                    () -> assertThat(post.getContent().getValue()).isEqualTo(NEW_CONTENT)
            );
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " "})
        void 제목이_비어_있으면_예외가_발생한다(String blankTitle) {
            // given
            PostUpdateCommand command = new PostUpdateCommand(postId, TEST_WRITER_ID, blankTitle, TEST_CONTENT);

            // when then
            assertThatThrownBy(() -> postService.update(command))
                    .isInstanceOf(BusinessException.class)
                    .hasMessage(PostException.INVALID_TITLE.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " "})
        void 내용이_비어_있으면_예외가_발생한다(String blankContent) {
            // given
            PostUpdateCommand command = new PostUpdateCommand(postId, TEST_WRITER_ID, TEST_TITLE, blankContent);

            // when then
            assertThatThrownBy(() -> postService.update(command))
                    .isInstanceOf(BusinessException.class)
                    .hasMessage(PostException.INVALID_CONTENT.getMessage());
        }

        @Test
        void 작성자가_일치하지_않으면_예외가_발생한다() {
            // given
            Long newWriterId = 2L;
            PostUpdateCommand command = new PostUpdateCommand(postId, newWriterId, NEW_TITLE, NEW_CONTENT);

            // when then
            assertThatThrownBy(() -> postService.update(command))
                    .isInstanceOf(BusinessException.class)
                    .hasMessage(PostException.UNAUTHORIZED_WRITER.getMessage());
        }

        @Test
        void 존재하지_않는_게시글이면_예외가_발생한다() {
            // given
            Long notExistPostId = 2L;
            PostUpdateCommand command = new PostUpdateCommand(notExistPostId, TEST_WRITER_ID, NEW_TITLE, NEW_CONTENT);

            // when then
            assertThatThrownBy(() -> postService.update(command))
                    .isInstanceOf(BusinessException.class)
                    .hasMessage(PostException.NOT_FOUND_POST.getMessage());
        }
    }
}
