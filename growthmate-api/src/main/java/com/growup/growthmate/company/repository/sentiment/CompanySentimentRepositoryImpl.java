package com.growup.growthmate.company.repository.sentiment;

import com.growup.growthmate.company.repository.sentiment.projection.CompanySentimentProjection;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.growup.growthmate.company.domain.QCompanySentiment.companySentiment;

@RequiredArgsConstructor
public class CompanySentimentRepositoryImpl implements CompanySentimentRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public CompanySentimentProjection findCompanySentiment(Long companyId) {
        return jpaQueryFactory.select(CompanySentimentProjection.CONSTRUCTOR_EXPRESSION)
                .from(companySentiment)
                .where(companySentiment.companyId.eq(companyId))
                .fetchOne();
    }
}
