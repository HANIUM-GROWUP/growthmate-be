package com.growup.growthmate.company.dto.find;

public record SortedCompanyResponse(
        Long companyId,
        String name,
        String imageUrl,
        String businessType) {
}
