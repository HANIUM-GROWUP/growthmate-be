package com.growup.growthmate.query.application;

import com.growup.growthmate.isolation.TestIsolation;
import com.growup.growthmate.query.dto.PostDetailRequest;
import com.growup.growthmate.query.dto.PostDetailResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static com.growup.growthmate.fixture.CommunityFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
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
            Long loginId = 1L;
            PostDetailRequest request = new PostDetailRequest(POST_ID, loginId);

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
    }
}