package com.growup.growthmate.query.application;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.community.post.exception.PostException;
import com.growup.growthmate.isolation.TestIsolation;
import com.growup.growthmate.query.dto.PagingParams;
import com.growup.growthmate.query.dto.request.CommentQueryRequest;
import com.growup.growthmate.query.dto.request.PostDetailRequest;
import com.growup.growthmate.query.dto.response.CommentResponse;
import com.growup.growthmate.query.dto.response.PostDetailResponse;
import com.growup.growthmate.query.dto.response.PostPreviewResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static com.growup.growthmate.fixture.CommunityFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@TestIsolation
@Sql(scripts = "/community_fixture.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class CommunityQueryServiceTest {

    @Autowired
    private CommunityQueryService communityQueryService;


    @Nested
    @DisplayName("게시글 상세 조회할 때")
    class PostDetailTest {

        @Test
        void 정상적으로_조회한다() {
            // given
            PostDetailRequest request = new PostDetailRequest(FIRST_POST_ID, POST_WRITER_ID);

            // when
            PostDetailResponse actual = communityQueryService.findPostDetail(request);

            // then
            assertAll(
                    () -> assertThat(actual.writer()).isEqualTo(MEMBER_NAME),
                    () -> assertThat(actual.title()).isEqualTo(POST_TITLE),
                    () -> assertThat(actual.content()).isEqualTo(POST_CONTENT),
                    () -> assertThat(actual.isMine()).isEqualTo(true)
            );
        }

        @Test
        void 로그인_회원이_작성자가_아니면_isMine이_false_반환한다() {
            // given
            Long loginId = 100L;
            PostDetailRequest request = new PostDetailRequest(FIRST_POST_ID, loginId);

            // when
            PostDetailResponse actual = communityQueryService.findPostDetail(request);

            // then
            assertThat(actual.isMine()).isEqualTo(false);
        }

        @Test
        void 게시글이_존재하지_않으면_예외가_발생한다() {
            // given
            Long postId = 100L;
            PostDetailRequest request = new PostDetailRequest(postId, POST_WRITER_ID);

            // when then
            assertThatThrownBy(() -> communityQueryService.findPostDetail(request))
                    .isInstanceOf(BusinessException.class)
                    .hasMessage(PostException.NOT_FOUND_POST.getMessage());
        }

        @Test
        void 삭제된_게시글은_조회되지_않는다() {
            // given
            PostDetailRequest request = new PostDetailRequest(DELETED_POST_ID, POST_WRITER_ID);

            // when then
            assertThatThrownBy(() -> communityQueryService.findPostDetail(request))
                    .isInstanceOf(BusinessException.class)
                    .hasMessage(PostException.NOT_FOUND_POST.getMessage());
        }
    }

    @Nested
    @DisplayName("게시글 목록을 조회할 때")
    class PostPreviewsTest {

        @Test
        void companyId만으로_조회하면_최근_10개가_조회된다() {
            // given
            PagingParams params = new PagingParams(null, null);

            // when
            List<PostPreviewResponse> actual = communityQueryService.findPostPreviews(COMPANY_ID, params);

            // then
            assertThat(actual)
                    .map(PostPreviewResponse::postId)
                    .containsExactly(14L, 13L, 12L, 11L, 10L, 9L, 8L, 7L, 6L, 5L);
        }

        @Test
        void cursor를_지정하면_cursor_이전에_만든_게시글을_조회한다() {
            // given
            PagingParams params = new PagingParams(13L, null);

            // when
            List<PostPreviewResponse> actual = communityQueryService.findPostPreviews(COMPANY_ID, params);

            // then
            assertThat(actual)
                    .map(PostPreviewResponse::postId)
                    .containsExactly(12L, 11L, 10L, 9L, 8L, 7L, 6L, 5L, 4L, 3L);
        }

        @Test
        void 삭제된_게시글은_조회되지_않는다() {
            // given
            PagingParams params = new PagingParams(5L, null);

            // when
            List<PostPreviewResponse> actual = communityQueryService.findPostPreviews(COMPANY_ID, params);

            // then
            assertThat(actual)
                    .map(PostPreviewResponse::postId)
                    .containsExactly(4L, 3L, 1L);
        }

        @Test
        void size만큼_조회된다() {
            // given
            PagingParams params = new PagingParams(null, 3);

            // when
            List<PostPreviewResponse> actual = communityQueryService.findPostPreviews(COMPANY_ID, params);

            // then
            assertThat(actual)
                    .map(PostPreviewResponse::postId)
                    .containsExactly(14L, 13L, 12L);
        }

        @Test
        void 게시글_본문이_요약되어_조회된다() {
            // given
            PagingParams params = new PagingParams(2L, 1);

            // when
            List<PostPreviewResponse> actual = communityQueryService.findPostPreviews(COMPANY_ID, params);

            // then
            assertThat(actual)
                    .map(PostPreviewResponse::preview)
                    .containsExactly("게시글 내용입니다.");
        }

        @Test
        void 댓글_개수가_알맞게_조회된다() {
            // given
            PagingParams params = new PagingParams(null, 4);

            // when
            List<PostPreviewResponse> actual = communityQueryService.findPostPreviews(COMPANY_ID, params);

            // then
            assertThat(actual)
                    .map(PostPreviewResponse::commentCount)
                    .containsExactly(3L, 2L, 1L, 13L);
        }
    }

    @Nested
    @DisplayName("게시글의 댓글 목록을 조회할 때")
    class CommentQueryTest {

        @Test
        void postId만으로_조회하면_최신_10개를_조회한다() {
            // given
            CommentQueryRequest request = new CommentQueryRequest(11L, COMMENT_WRITER_ID);
            PagingParams params = new PagingParams(null, null);

            // when
            List<CommentResponse> actual = communityQueryService.findComments(request, params);

            // then
            assertAll(
                    () -> assertThat(actual)
                            .map(CommentResponse::commentId)
                            .containsExactly(20L, 19L, 18L, 17L, 16L, 15L, 14L, 13L, 12L, 11L),
                    () -> assertThat(actual)
                            .map(CommentResponse::content)
                            .containsOnly(COMMENT_CONTENT),
                    () -> assertThat(actual)
                            .map(CommentResponse::isMine)
                            .containsOnly(true)
            );
        }

        @Test
        void 로그인_회원이_작성자가_아니면_isMine이_false를_반환한다() {
            // given
            Long loginId = 100L;
            CommentQueryRequest request = new CommentQueryRequest(11L, loginId);
            PagingParams params = new PagingParams(null, null);

            // when
            List<CommentResponse> actual = communityQueryService.findComments(request, params);

            // then
            assertThat(actual)
                    .map(CommentResponse::isMine)
                    .containsOnly(false);
        }

        @Test
        void cursor를_지정하면_cursor_이전에_만든_게시글을_조회한다() {
            // given
            CommentQueryRequest request = new CommentQueryRequest(11L, COMMENT_WRITER_ID);
            PagingParams params = new PagingParams(19L, null);

            // when
            List<CommentResponse> actual = communityQueryService.findComments(request, params);

            // then
            assertThat(actual)
                    .map(CommentResponse::commentId)
                    .containsExactly(18L, 17L, 16L, 15L, 14L, 13L, 12L, 11L, 10L, 9L);
        }

        @Test
        void 삭제된_댓글은_조회되지_않는다() {
            // given
            CommentQueryRequest request = new CommentQueryRequest(11L, 1L);
            PagingParams params = new PagingParams(12L, null);

            // when
            List<CommentResponse> actual = communityQueryService.findComments(request, params);

            // then
            assertThat(actual)
                    .map(CommentResponse::commentId)
                    .containsExactly(11L, 10L, 9L, 7L);
        }

        @Test
        void size만큼_조회된다() {
            // given
            CommentQueryRequest request = new CommentQueryRequest(11L, 1L);
            PagingParams params = new PagingParams(null, 3);

            // when
            List<CommentResponse> actual = communityQueryService.findComments(request, params);

            // then
            assertThat(actual)
                    .map(CommentResponse::commentId)
                    .containsExactly(20L, 19L, 18L);
        }
    }
}