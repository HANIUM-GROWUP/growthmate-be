package com.growup.growthmate.query.dto.response;

import java.time.LocalDateTime;

public record PostDetailResponse(
        String writer,
        String title,
        String content,
        LocalDateTime createDate,
        Boolean isMine
) {
}
