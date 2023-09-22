package com.growup.growthmate.company.dto.detail;

import java.math.BigInteger;
import java.time.LocalDateTime;

public record CompanyDetailResponse(
        String name,
        String imageUrl,
        String businessType,
        LocalDateTime establishmentDate,
        String address,
        BigInteger employeeNumber,
        BigInteger sales
) {
}
