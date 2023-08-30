package com.growup.growthmate.query.repository.projection;

import java.time.LocalDateTime;

public interface PostDetailProjection {

    String getName();
    String getTitle();
    String getContent();
    LocalDateTime getCreated_at();
    Long getMember_id();
}
