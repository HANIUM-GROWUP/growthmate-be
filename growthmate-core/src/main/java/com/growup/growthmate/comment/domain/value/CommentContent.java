package com.growup.growthmate.comment.domain.value;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter
public class CommentContent {

    @Column(name = "content", nullable = false)
    @Lob
    private String value;

    public CommentContent(String value) {
        this.value = value;
    }
}
