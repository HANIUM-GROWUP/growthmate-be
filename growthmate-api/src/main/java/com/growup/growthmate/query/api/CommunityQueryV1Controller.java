package com.growup.growthmate.query.api;

import com.growup.growthmate.LoginMember;
import com.growup.growthmate.query.application.CommunityQueryService;
import com.growup.growthmate.query.dto.PagingParams;
import com.growup.growthmate.query.dto.PostDetailRequest;
import com.growup.growthmate.query.dto.PostDetailResponse;
import com.growup.growthmate.query.dto.PostPreviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/companies/{companyId}/posts")
    public ResponseEntity<List<PostPreviewResponse>> findPosts(@PathVariable Long companyId,
                                                               @ModelAttribute PagingParams params) {
        List<PostPreviewResponse> responses = communityQueryService.findPostPreviews(companyId, params);
        return ResponseEntity.ok(responses);
    }
}
