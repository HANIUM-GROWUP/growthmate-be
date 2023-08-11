package com.growup.growthmate.comment.domain.value;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class PostId {

    @Column(nullable = false, name = "post_id")
    private Long value;
}
