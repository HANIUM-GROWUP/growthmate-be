package com.growup.growthmate.comment.domain.value;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.comment.exception.CommentException;
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
        validateContentLength(value);
        this.value = value;
    }

    private void validateContentLength(String value) {
        if (value == null || value.isBlank()) {
            CommentException exception = CommentException.INVALID_CONTENT;
            throw new BusinessException(exception.getHttpStatusCode(), exception.getMessage());
        }
    }
}
