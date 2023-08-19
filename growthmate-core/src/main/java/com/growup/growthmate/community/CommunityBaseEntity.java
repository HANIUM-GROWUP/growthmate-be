package com.growup.growthmate.community;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class CommunityBaseEntity {

    @Embedded
    private WriterId writerId;

    @Column(nullable = false)
    private boolean isDeleted;

    protected CommunityBaseEntity(WriterId writerId) {
        this.writerId = writerId;
        this.isDeleted = false;
    }

    public boolean isSameWriterId(WriterId writerId) {
        return this.writerId.equals(writerId);
    }
}
