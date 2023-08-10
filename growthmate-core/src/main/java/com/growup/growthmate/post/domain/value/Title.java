package com.growup.growthmate.post.domain.value;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.post.exception.PostException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter
public class Title {

    @Column(name = "title", nullable = false)
    private String value;

    public Title(String value) {
        validateValue(value);
        this.value = value;
    }

    private void validateValue(String value) {
        if (value == null || value.isBlank()) {
            PostException exception = PostException.INVALID_TITLE;
            throw new BusinessException(exception.getHttpStatusCode(), exception.getMessage());
        }
    }
}
