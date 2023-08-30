package com.growup.growthmate.query.application;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.community.post.exception.PostException;
import com.growup.growthmate.isolation.TestIsolation;
import com.growup.growthmate.query.dto.PagingParams;
import com.growup.growthmate.query.dto.PostDetailRequest;
import com.growup.growthmate.query.dto.PostDetailResponse;
import com.growup.growthmate.query.dto.PostPreviewResponse;
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
            PostDetailRequest request = new PostDetailRequest(FIRST_POST_ID, FIRST_POST_WRITER_ID);

            // when
            PostDetailResponse actual = communityQueryService.findPostDetail(request);

            // then
            assertAll(
                    () -> assertThat(actual.writer()).isEqualTo(MEMBER_NAME),
                    () -> assertThat(actual.title()).isEqualTo(FIRST_POST_TITLE),
                    () -> assertThat(actual.content()).isEqualTo(FIRST_POST_CONTENT),
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
            PostDetailRequest request = new PostDetailRequest(postId, FIRST_POST_WRITER_ID);

            // when then
            assertThatThrownBy(() -> communityQueryService.findPostDetail(request))
                    .isInstanceOf(BusinessException.class)
                    .hasMessage(PostException.NOT_FOUND_POST.getMessage());
        }

        @Test
        void 삭제된_게시글은_조회되지_않는다() {
            // given
            PostDetailRequest request = new PostDetailRequest(DELETED_POST_ID, FIRST_POST_WRITER_ID);

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
            assertAll(
                    () -> assertThat(actual)
                            .map(PostPreviewResponse::postId)
                            .containsExactly(12L, 11L, 10L, 9L, 8L, 7L, 6L, 5L, 4L, 3L)
            );
        }

        @Test
        void cursor를_지정하면_cursor_이전에_만든_게시글을_조회한다() {
            // given

            // when

            // then
        }

        @Test
        void 삭제된_게시글은_조회되지_않는다() {
            // given

            // when

            // then
        }

        @Test
        void size만큼_조회된다() {
            // given

            // when

            // then
        }

        @Test
        void 게시글_본문이_요약되어_조회된다() {
            // given

            // when

            // then
        }
    }
}