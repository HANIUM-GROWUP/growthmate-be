package com.growup.growthmate.member.application;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.member.domain.Member;
import com.growup.growthmate.member.domain.MemberRepository;
import com.growup.growthmate.member.dto.MemberInfoResponse;
import com.growup.growthmate.member.dto.MemberRequest;
import com.growup.growthmate.member.dto.MemberUpdateInfoRequest;
import com.growup.growthmate.member.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member join(MemberRequest request) {

        MemberException duplicated = MemberException.MEMBER_DUPLICATE_EMAIL;

        if (memberRepository.existsByEmail(request.email())) {
            throw new BusinessException(duplicated.getHttpStatusCode(), duplicated.getMessage());
        }
        return memberRepository.save(request.toEntity());
    }

    @Transactional
    public void updateName(MemberUpdateInfoRequest memberUpdateInfoRequest) {

        Member member = getMember(memberUpdateInfoRequest);
        member.updateName(memberUpdateInfoRequest.name());
    }

    @Transactional
    public MemberInfoResponse getMemberInfo(Long memberId) {

        Optional<Member> result = memberRepository.findById(memberId);
        return MemberInfoResponse.builder()
                .name(result.get().getName())
                .email(result.get().getEmail())
                .picture(result.get().getPictureUrl())
                .build();
    }

    private Member getMember(MemberUpdateInfoRequest memberUpdateInfoRequest) {

        MemberException notFound = MemberException.NO_FOUND_MEMBER;

        Member member = memberRepository.findById(memberUpdateInfoRequest.memberId())
                .orElseThrow(() -> new BusinessException(notFound.getHttpStatusCode(), notFound.getMessage()));

        return member;
    }
}

