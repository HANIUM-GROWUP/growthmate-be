package com.growup.growthmate.community.post.domain.value;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class CompanyId {

    @Column(nullable = false, name = "company_id")
    private Long value;
}
