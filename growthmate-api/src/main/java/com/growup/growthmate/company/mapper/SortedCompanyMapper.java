package com.growup.growthmate.company.mapper;

import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.company.dto.find.SortedCompanyResponse;
import com.growup.growthmate.query.dto.response.PostPreviewResponse;
import com.growup.growthmate.query.repository.projection.PostPreviewProjection;
import org.springframework.stereotype.Component;

@Component
public class SortedCompanyMapper {

    public SortedCompanyResponse toResponse(Company company) {
        return new SortedCompanyResponse(
                company.getId(),
                company.getName(),
                company.getImageUrl(),
                company.getBusinessType()
        );
    }

}
