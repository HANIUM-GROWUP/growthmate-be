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
        validateDuplicateEmail(request.email());
        return memberRepository.save(request.toEntity());
    }

    @Transactional
    public void updateName(MemberUpdateInfoRequest memberUpdateInfoRequest) {

        Member member = getMember(memberUpdateInfoRequest);
        member.updateName(memberUpdateInfoRequest.name());
    }

    public MemberInfoResponse getMemberInfo(Long memberId) {
        return memberRepository.findById(memberId)
                .map(member -> new MemberInfoResponse(member.getName(), member.getEmail(), member.getPictureUrl()))
                .orElseThrow(() -> new BusinessException(
                        MemberException.NO_FOUND_MEMBER.getHttpStatusCode(),
                        MemberException.NO_FOUND_MEMBER.getMessage())
                );
    }

    private Member getMember(MemberUpdateInfoRequest memberUpdateInfoRequest) {

        MemberException notFound = MemberException.NO_FOUND_MEMBER;

        Member member = memberRepository.findById(memberUpdateInfoRequest.memberId())
                .orElseThrow(() -> new BusinessException(notFound.getHttpStatusCode(), notFound.getMessage()));

        return member;
    }

    private void validateDuplicateEmail(String email) {
        MemberException duplicated = MemberException.MEMBER_DUPLICATE_EMAIL;
        if (memberRepository.existsByEmail(email)) {
            throw new BusinessException(duplicated.getHttpStatusCode(), duplicated.getMessage());
        }
    }
}
