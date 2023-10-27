package com.growup.growthmate.company.repository.news.projection;

import com.growup.growthmate.company.domain.Sentiment;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.growup.growthmate.company.domain.QCompanyNews.companyNews;

@AllArgsConstructor
@Getter
public class CompanyNewsProjection {

    public static final ConstructorExpression<CompanyNewsProjection> CONSTRUCTOR_EXPRESSION =
            Projections.constructor(CompanyNewsProjection.class,
                    companyNews.companyId,
                    companyNews.title,
                    companyNews.description,
                    companyNews.url,
                    companyNews.sentiment
            );

    private Long companyId;
    private String title;
    private String description;
    private String url;
    private Sentiment sentiment;

}
