package com.growup.growthmate.post.domain.value;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.post.exception.PostException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.*;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter
public class PostContent {

    @Column(name = "content", nullable = false)
    @Lob
    private String value;

    public PostContent(String value) {
        validateContentLength(value);
        this.value = value;
    }

    private static void validateContentLength(String value) {
        if (value == null || value.isBlank()) {
            PostException exception = PostException.INVALID_CONTENT;
            throw new BusinessException(exception.getHttpStatusCode(), exception.getMessage());
        }
    }
}
