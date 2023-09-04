package com.growup.growthmate.query.dto;

import com.growup.growthmate.query.dto.response.CommentResponse;
import com.growup.growthmate.query.dto.response.PostDetailResponse;
import com.growup.growthmate.query.dto.response.PostPreviewResponse;
import com.growup.growthmate.query.repository.projection.CommentProjection;
import com.growup.growthmate.query.repository.projection.PostDetailProjection;
import com.growup.growthmate.query.repository.projection.PostPreviewProjection;
import org.springframework.stereotype.Component;

@Component
public class CommunityResponseMapper {

    public PostDetailResponse toResponse(PostDetailProjection projection, boolean isMine) {
        return new PostDetailResponse(
                projection.getName(),
                projection.getTitle(),
                projection.getContent(),
                projection.getCreated_at(),
                isMine
        );
    }

    public PostPreviewResponse toResponse(PostPreviewProjection projection) {
        return new PostPreviewResponse(
                projection.getPostId(),
                projection.getName(),
                projection.getTitle(),
                projection.getContent(),
                projection.getCreatedAt(),
                projection.getCommentCount()
        );
    }

    public CommentResponse toResponse(CommentProjection projection, boolean isMine) {
        return new CommentResponse(
                projection.getCommentId(),
                projection.getName(),
                projection.getContent(),
                isMine
        );
    }
}
