package com.growup.growthmate.query.api;

import com.growup.growthmate.LoginMember;
import com.growup.growthmate.query.application.CommunityQueryService;
import com.growup.growthmate.query.dto.*;
import com.growup.growthmate.query.dto.request.CommentQueryRequest;
import com.growup.growthmate.query.dto.request.PostDetailRequest;
import com.growup.growthmate.query.dto.response.CommentResponse;
import com.growup.growthmate.query.dto.response.PostDetailResponse;
import com.growup.growthmate.query.dto.response.PostPreviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
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

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponse>> findComments(@PathVariable Long postId,
                                                              @ModelAttribute PagingParams params,
                                                              LoginMember loginMember) {
        CommentQueryRequest request = new CommentQueryRequest(postId, loginMember.id());
        List<CommentResponse> responses = communityQueryService.findComments(request, params);
        return ResponseEntity.ok(responses);
    }
}
