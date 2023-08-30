package com.growup.growthmate.query.repository.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PostPreviewProjection {

    private Long postId;
    private String name;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Long commentCount;
}