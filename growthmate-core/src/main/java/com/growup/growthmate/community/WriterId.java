package com.growup.growthmate.community;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class WriterId {

    @Column(nullable = false, name = "member_id")
    private Long value;
}
