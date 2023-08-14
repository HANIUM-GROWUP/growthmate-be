package com.growup.growthmate.member.api;

import com.growup.growthmate.LoginMember;
import com.growup.growthmate.member.application.MemberService;
import com.growup.growthmate.member.dto.MemberInfoResponse;
import com.growup.growthmate.member.dto.MemberUpdateInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberV1Controller {

    private final MemberService memberService;

    @PatchMapping(value = "/me")
    public ResponseEntity<Void> updateMemberInfo(@RequestBody MemberUpdateInfoRequest memberInfo,
                                               LoginMember loginMember) {

        memberService.updateName(memberInfo);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/me")
    public ResponseEntity<MemberInfoResponse> getMemberInfo(LoginMember loginMember) {

        Long memberId = loginMember.id();

        MemberInfoResponse response = memberService.getMemberInfo(memberId);
        return ResponseEntity.ok(response);
    }

}
