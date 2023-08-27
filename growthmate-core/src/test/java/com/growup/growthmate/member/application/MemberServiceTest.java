package com.growup.growthmate.member.application;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.member.domain.Member;
import com.growup.growthmate.member.dto.MemberInfoResponse;
import com.growup.growthmate.member.dto.MemberRequest;
import com.growup.growthmate.member.dto.MemberUpdateInfoRequest;
import com.growup.growthmate.isolation.TestIsolation;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@TestIsolation
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private EntityManager entityManager;
    private final MemberRequest request = new MemberRequest(
            "google", "안정후", "ajh@gmail.com", "picture.com"
    );

    @Nested
    @DisplayName("사용자가 서비스에 가입할 때")
    class JoinTest {

        @Test
        void 사용자가_저장_된다() {
            // when
            Member member = memberService.join(request);

            // then
            assertThat(member.getId()).isNotNull();
        }

        @Test
        void 이미_가입된_email이면_예외가_발생한다() {
            // given
            memberService.join(request);

            // when then
            assertThatThrownBy(() -> memberService.join(request))
                    .isInstanceOf(BusinessException.class)
                    .extracting("message")
                    .isEqualTo("이미 등록된 이메일입니다.");
        }
    }

    @Nested
    @DisplayName("사용자가 이름을 수정할 때")
    class UpdateTest {

        private static final String NEW_NAME = "후후후";

        @Test
        void 이름을_수정한다() {

            //given
            MemberUpdateInfoRequest updateRequest = new MemberUpdateInfoRequest(1L, NEW_NAME);
            memberService.join(request);

            //when
            memberService.updateName(updateRequest);

            //then
            Member member = entityManager.find(Member.class, 1L);
            assertThat(member.getName().equals(NEW_NAME));
        }

    }

    @Nested
    @DisplayName("사용자가 이름 정보를 가져올 떄")
    class getTest {

        @Test
        void 사용자_정보를_가져온다() {

            //given
            memberService.join(request);

            //when
            MemberInfoResponse response = memberService.getMemberInfo(1L);

            //then
            Member member = entityManager.find(Member.class, 1L);
            assertThat(member.getName().equals(response.name()));
            assertThat(member.getEmail().equals(response.email()));
            assertThat(member.getPictureUrl().equals(response.picture()));
        }

    }
}
