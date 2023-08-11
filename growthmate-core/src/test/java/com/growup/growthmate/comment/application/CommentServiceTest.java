package com.growup.growthmate.comment.application;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.comment.domain.Comment;
import com.growup.growthmate.comment.dto.CommentCreateCommand;
import com.growup.growthmate.comment.exception.CommentException;
import com.growup.isolation.TestIsolation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
}
