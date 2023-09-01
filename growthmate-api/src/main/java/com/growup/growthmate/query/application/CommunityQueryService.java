package com.growup.growthmate.query.application;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.community.post.exception.PostException;
import com.growup.growthmate.query.dto.CommunityResponseMapper;
import com.growup.growthmate.query.dto.PagingParams;
import com.growup.growthmate.query.dto.request.CommentQueryRequest;
import com.growup.growthmate.query.dto.request.PostDetailRequest;
import com.growup.growthmate.query.dto.request.PostPreviewRequest;
import com.growup.growthmate.query.dto.response.CommentResponse;
import com.growup.growthmate.query.dto.response.PostDetailResponse;
import com.growup.growthmate.query.dto.response.PostPreviewResponse;
import com.growup.growthmate.query.repository.CommunityQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommunityQueryService {

    private final CommunityQueryRepository communityQueryRepository;
    private final CommunityResponseMapper mapper;

    public PostDetailResponse findPostDetail(PostDetailRequest request) {
        return communityQueryRepository.findPostDetail(request.postId())
                .map(post -> mapper.toResponse(post, isMine(post.getMember_id(), request.loginId())))
                .orElseThrow(this::throwNotFoundException);
    }

    private BusinessException throwNotFoundException() {
        PostException notFound = PostException.NOT_FOUND_POST;
        return new BusinessException(notFound.getHttpStatusCode(), notFound.getMessage());
    }

    public List<PostPreviewResponse> findPostPreviews(PostPreviewRequest request) {
        return communityQueryRepository.findPostPreviews(request.companyId(), request.cursor(), request.size()).stream()
                .map(mapper::toResponse)
                .toList();
    }

    public List<CommentResponse> findComments(CommentQueryRequest request, PagingParams params) {
        return communityQueryRepository.findComments(request.postId(), params.getCursor(), params.getSize()).stream()
                .map(post -> mapper.toResponse(post, isMine(post.getMemberId(), request.loginId())))
                .toList();
    }

    private boolean isMine(Long memberId, Long loginId) {
        return memberId.equals(loginId);
    }
}
