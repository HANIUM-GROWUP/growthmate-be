package com.growup.growthmate.company.repository.news;

import com.growup.growthmate.company.dto.news.CompanyNewsRequest;
import com.growup.growthmate.company.repository.news.projection.CompanyNewsProjection;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.growup.growthmate.company.domain.QCompanyNews.companyNews;

@RequiredArgsConstructor
public class CompanyNewsRepositoryImpl implements CompanyNewsRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CompanyNewsProjection> findCompanyNewsList(CompanyNewsRequest request) {
        return queryFactory.select(CompanyNewsProjection.CONSTRUCTOR_EXPRESSION)
                .from(companyNews)
                .where(companyNews.companyId.eq(request.companyId())
                .and(getCursorCondition(request.cursor(), companyNews.id)))
                .orderBy(companyNews.id.desc())
                .limit(request.size())
                .fetch();
    }

    private BooleanBuilder getCursorCondition(Long cursor, NumberPath<Long> id) {
        if (cursor == null) {
            return new BooleanBuilder();
        }
        return new BooleanBuilder(id.lt(cursor));
    }
}
