package com.growup.growthmate.company.repository.news;

import com.growup.growthmate.company.repository.news.projection.CompanyNewsProjection;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.growup.growthmate.company.domain.QCompanyNews.companyNews;

@RequiredArgsConstructor
public class CompanyNewsRepositoryImpl implements CompanyNewsRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CompanyNewsProjection> findCompanyNewsList(Long companyId) {
        return queryFactory.select(CompanyNewsProjection.CONSTRUCTOR_EXPRESSION)
                .from(companyNews)
                .where(companyNews.companyId.eq(companyId))
                .fetch();
    }
}
