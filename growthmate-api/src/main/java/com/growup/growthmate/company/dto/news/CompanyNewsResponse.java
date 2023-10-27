package com.growup.growthmate.company.dto.news;

import com.growup.growthmate.company.domain.Sentiment;

public record CompanyNewsResponse(Long companyNewsId,
                                  String title,
                                  String description,
                                  String url,
                                  Sentiment sentiment) {
}
