package com.growup.growthmate.member.api;

import com.growup.growthmate.LoginMember;
import com.growup.growthmate.auth.application.AuthService;
import com.growup.growthmate.auth.dto.LoginRequest;
import com.growup.growthmate.auth.dto.LoginResponse;
import com.growup.growthmate.auth.security.authentication.PrincipalOAuth2UserService;
import com.growup.growthmate.member.application.MemberService;
import com.growup.growthmate.member.dto.MemberInfoResponse;
import com.growup.growthmate.member.dto.MemberUpdateInfoRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "MemberController", description = "멤버 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberV1Controller {

    private final MemberService memberService;
    private final PrincipalOAuth2UserService principalOAuth2UserService;

    @Operation(summary = "회원 로그인 api", description = "login")
    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> authLogin(@RequestBody LoginRequest loginRequest) {

        LoginResponse response = principalOAuth2UserService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "회원 정보 수정 api", description = "updateMemberInfo")
    @PatchMapping(value = "/me")
    public ResponseEntity<Void> updateMemberInfo(@RequestBody MemberUpdateInfoRequest memberInfo,
                                               LoginMember loginMember) {

        memberService.updateName(memberInfo);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "회원 정보 조회 api", description = "getMemberInfo")
    @GetMapping(value = "/me")
    public ResponseEntity<MemberInfoResponse> getMemberInfo(LoginMember loginMember) {

        Long memberId = loginMember.id();

        MemberInfoResponse response = memberService.getMemberInfo(memberId);
        return ResponseEntity.ok(response);
    }

}
