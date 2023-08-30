package com.growup.growthmate.query.dto;

import java.time.LocalDateTime;

public record PostPreviewResponse(
        Long postId,
        String writer,
        String title,
        String preview,
        LocalDateTime createDate,
        Long commentCount
) {
}
