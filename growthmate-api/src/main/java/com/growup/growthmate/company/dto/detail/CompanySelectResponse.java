package com.growup.growthmate.company.dto.detail;

import java.time.LocalDateTime;

public record CompanySelectResponse(
        Long id,
        String name,
        String imageUrl,
        String businessType) {
}
