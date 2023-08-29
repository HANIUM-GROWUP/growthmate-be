package com.growup.growthmate.query.api;

import com.growup.growthmate.LoginMember;
import com.growup.growthmate.query.application.CommunityQueryService;
import com.growup.growthmate.query.dto.PostDetailRequest;
import com.growup.growthmate.query.dto.PostDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi")
@RequiredArgsConstructor
public class CommunityQueryV1Controller {

    private final CommunityQueryService communityQueryService;

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDetailResponse> findPost(@PathVariable Long postId, LoginMember loginMember) {
        PostDetailRequest request = new PostDetailRequest(postId, loginMember.id());
        PostDetailResponse response = communityQueryService.findPostDetail(request);
        return ResponseEntity.ok(response);
    }
}
