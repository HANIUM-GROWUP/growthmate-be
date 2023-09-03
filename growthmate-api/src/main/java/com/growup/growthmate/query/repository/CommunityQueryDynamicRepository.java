package com.growup.growthmate.query.repository;

import com.growup.growthmate.query.repository.projection.CommentProjection;
import com.growup.growthmate.query.repository.projection.PostPreviewProjection;

import java.util.List;

public interface CommunityQueryDynamicRepository {

    List<PostPreviewProjection> findPostPreviews(Long companyId, Long cursor, Integer size);

    List<CommentProjection> findComments(Long postId, Long cursor, Integer size);
}
