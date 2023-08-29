package com.growup.growthmate.query.repository;

import java.time.LocalDateTime;

public interface PostDetailProjection {

    String getName();
    String getTitle();
    String getContent();
    LocalDateTime getCreated_at();
    Long getMember_id();
}
