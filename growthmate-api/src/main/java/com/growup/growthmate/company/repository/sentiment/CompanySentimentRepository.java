package com.growup.growthmate.company.repository.sentiment;

import com.growup.growthmate.company.repository.sentiment.projection.CompanySentimentProjection;

public interface CompanySentimentRepository {

    CompanySentimentProjection findCompanySentiment(Long companyId);

}
