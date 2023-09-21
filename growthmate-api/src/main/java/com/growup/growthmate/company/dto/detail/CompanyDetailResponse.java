package com.growup.growthmate.company.dto.detail;

import java.math.BigInteger;

public record CompanyDetailResponse(
                String name,
                String imageUrl,
                String businessType,
                String establishmentDate,
                String address,
                BigInteger employeeNumber,
                BigInteger sales
) {
}
