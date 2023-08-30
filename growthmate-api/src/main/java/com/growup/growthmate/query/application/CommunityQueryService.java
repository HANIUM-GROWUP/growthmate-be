package com.growup.growthmate.query.application;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.community.post.exception.PostException;
import com.growup.growthmate.query.dto.PagingParams;
import com.growup.growthmate.query.dto.PostDetailRequest;
import com.growup.growthmate.query.dto.PostDetailResponse;
import com.growup.growthmate.query.dto.PostPreviewResponse;
import com.growup.growthmate.query.repository.projection.PostDetailProjection;
import com.growup.growthmate.query.repository.PostQueryRepository;
import com.growup.growthmate.query.repository.projection.PostPreviewProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommunityQueryService {

    private final PostQueryRepository postQueryRepository;

    public PostDetailResponse findPostDetail(PostDetailRequest request) {
        return postQueryRepository.findPostDetail(request.postId())
                .map(post -> toPostDetailResponse(post, request.loginId()))
                .orElseThrow(this::throwNotFoundException);
    }

    private PostDetailResponse toPostDetailResponse(PostDetailProjection projection, Long loginId) {
        return new PostDetailResponse(
                projection.getName(),
                projection.getTitle(),
                projection.getContent(),
                projection.getCreated_at(),
                isMine(projection.getMember_id(), loginId)
        );
    }

    private boolean isMine(Long memberId, Long loginId) {
        return memberId.equals(loginId);
    }

    private BusinessException throwNotFoundException() {
        PostException notFound = PostException.NOT_FOUND_POST;
        return new BusinessException(notFound.getHttpStatusCode(), notFound.getMessage());
    }

    public List<PostPreviewResponse> findPostPreviews(Long companyId, PagingParams params) {
        return postQueryRepository.findPostPreviews(companyId, params.getCursor(), params.getSize()).stream()
                .map(this::getPostPreviewResponse)
                .toList();
    }

    private PostPreviewResponse getPostPreviewResponse(PostPreviewProjection projection) {
        return new PostPreviewResponse(
                projection.getPostId(),
                projection.getName(),
                projection.getTitle(),
                projection.getContent().substring(0, 10),
                projection.getCreatedAt(),
                projection.getCommentCount()
        );
    }
}
