package com.growup.growthmate.query.repository;

import com.growup.growthmate.query.repository.projection.PostPreviewProjection;

import java.util.List;

public interface PostQueryDynamicRepository {

    List<PostPreviewProjection> findPostPreviews(Long companyId, Long cursor, Integer size);
}
