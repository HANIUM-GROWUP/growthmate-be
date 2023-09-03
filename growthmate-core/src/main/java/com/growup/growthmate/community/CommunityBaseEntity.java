package com.growup.growthmate.community;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommunityBaseEntity {

    @Embedded
    private WriterId writerId;

    @Column(nullable = false)
    private boolean isDeleted;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    protected CommunityBaseEntity(WriterId writerId) {
        this.writerId = writerId;
        this.isDeleted = false;
        this.createdAt = LocalDateTime.now();
    }

    public boolean isSameWriterId(WriterId writerId) {
        return this.writerId.equals(writerId);
    }
}
