package com.growup.growthmate.company.dto.detail;

import java.time.LocalDateTime;

public record CompanySelectResponse(
        String name,
        String imageUrl,
        String businessType,
        LocalDateTime establishmentDate) {
}
