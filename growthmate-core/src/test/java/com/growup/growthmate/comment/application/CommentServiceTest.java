package com.growup.growthmate.comment.application;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.comment.domain.Comment;
import com.growup.growthmate.comment.dto.CommentCreateCommand;
import com.growup.growthmate.comment.dto.CommentDeleteCommand;
import com.growup.growthmate.comment.dto.CommentUpdateCommand;
import com.growup.growthmate.comment.exception.CommentException;
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
class CommentServiceTest {

    private static final Long TEST_WRITER_ID = 1L;
    private static final Long TEST_POST_ID = 2L;
    private static final String TEST_CONTENT = "이것은 댓글입니다.";

    @Autowired
    private CommentService commentService;

    @PersistenceContext
    private EntityManager entityManager;

    private Long commentId;

    @BeforeEach
    void initComment() {
        CommentCreateCommand command = new CommentCreateCommand(TEST_POST_ID, TEST_CONTENT, TEST_WRITER_ID);
        commentId = commentService.create(command);
    }

    @Nested
    @DisplayName("댓글을 생성할 때")
    class CreateTest {

        @Test
        void 유효한_내용은_생성한다() {
            // given
            CommentCreateCommand command = new CommentCreateCommand(TEST_POST_ID, TEST_CONTENT, TEST_WRITER_ID);

            // when
            Long commentId = commentService.create(command);

            // then
            Comment comment = entityManager.find(Comment.class, commentId);
            assertAll(
                    () -> assertThat(comment.getId()).isNotNull(),
                    () -> assertThat(comment.getPostId().getValue()).isEqualTo(TEST_POST_ID),
                    () -> assertThat(comment.getWriterId().getValue()).isEqualTo(TEST_WRITER_ID),
                    () -> assertThat(comment.getContent().getValue()).isEqualTo(TEST_CONTENT)
            );
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " "})
        void 내용이_비어_있으면_예외가_발생한다(String blankContent) {
            // given
            CommentCreateCommand command = new CommentCreateCommand(TEST_POST_ID, blankContent, TEST_WRITER_ID);

            // when then
            assertThatThrownBy(() -> commentService.create(command))
                    .isInstanceOf(BusinessException.class)
                    .hasMessage(CommentException.INVALID_CONTENT.getMessage());
        }
    }

    @Nested
    @DisplayName("댓글을 수정할 때")
    class UpdateTest {

        private static final String NEW_CONTENT = "변경된 댓글입니다.";

        @Test
        void 유효한_내용은_수정한다() {
            // given
            CommentUpdateCommand command = new CommentUpdateCommand(commentId, NEW_CONTENT, TEST_WRITER_ID);

            // when
            commentService.update(command);

            // then
            Comment comment = entityManager.find(Comment.class, commentId);
            assertThat(comment.getContent().getValue()).isEqualTo(NEW_CONTENT);
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " "})
        void 내용이_비어_있으면_예외가_발생한다(String blankContent) {
            // given
            CommentUpdateCommand command = new CommentUpdateCommand(commentId, blankContent, TEST_WRITER_ID);

            // when then
            assertThatThrownBy(() -> commentService.update(command))
                    .isInstanceOf(BusinessException.class)
                    .hasMessage(CommentException.INVALID_CONTENT.getMessage());
        }

        @Test
        void 존재하지_않는_댓글이면_예외가_발생한다() {
            // given
            Long notExistCommentId = 100L;
            CommentUpdateCommand command = new CommentUpdateCommand(notExistCommentId, NEW_CONTENT, TEST_WRITER_ID);

            // when then
            assertThatThrownBy(() -> commentService.update(command))
                    .isInstanceOf(BusinessException.class)
                    .hasMessage(CommentException.NOT_FOUND_COMMENT.getMessage());

        }

        @Test
        void 작성자가_같지_않으면_예외가_발생한다() {
            // given
            Long inValidWriterId = 100L;
            CommentUpdateCommand command = new CommentUpdateCommand(commentId, NEW_CONTENT, inValidWriterId);

            // when then
            assertThatThrownBy(() -> commentService.update(command))
                    .isInstanceOf(BusinessException.class)
                    .hasMessage(CommentException.UNAUTHORIZED_WRITER.getMessage());
        }
    }

    @Nested
    @DisplayName("댓글을 삭제할 때")
    class DeleteTest {
        @Test
        void 유효한_작성자면_삭제한다() {
            // given
            CommentDeleteCommand command = new CommentDeleteCommand(commentId, TEST_WRITER_ID);

            // when
            commentService.delete(command);

            // then
            Comment comment = entityManager.find(Comment.class, commentId);
            assertThat(comment).isNull();
        }

        @Test
        void 유효하지_않은_작성자면_예외가_발생한다() {
            // given
            CommentDeleteCommand command = new CommentDeleteCommand(commentId, 2L);

            // when then
            assertThatThrownBy(() -> commentService.delete(command))
                    .isInstanceOf(BusinessException.class)
                    .hasMessage(CommentException.UNAUTHORIZED_WRITER.getMessage());
        }
    }
}
