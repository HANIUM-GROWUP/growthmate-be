package com.growup.growthmate.query.application;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.community.post.exception.PostException;
import com.growup.growthmate.query.dto.PostDetailRequest;
import com.growup.growthmate.query.dto.PostDetailResponse;
import com.growup.growthmate.query.repository.PostDetailProjection;
import com.growup.growthmate.query.repository.PostQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
