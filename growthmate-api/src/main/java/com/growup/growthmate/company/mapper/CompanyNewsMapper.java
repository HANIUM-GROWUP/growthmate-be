package com.growup.growthmate.company.mapper;

import com.growup.growthmate.company.dto.news.CompanyNewsResponse;
import com.growup.growthmate.company.repository.news.projection.CompanyNewsProjection;
import org.springframework.stereotype.Component;

@Component
public class CompanyNewsMapper {

    public CompanyNewsResponse toResponse(CompanyNewsProjection projection) {
        return new CompanyNewsResponse(
                projection.getCompanyNewsId(),
                projection.getTitle(),
                projection.getDescription(),
                projection.getUrl(),
                projection.getSentiment()
        );
    }

}
