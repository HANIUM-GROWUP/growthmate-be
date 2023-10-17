package com.growup.growthmate.company.repository.growth;

import com.growup.growthmate.company.repository.growth.projection.CompanyGrowthProjection;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.growup.growthmate.company.domain.QCompanyGrowth.companyGrowth;

@RequiredArgsConstructor
public class CompanyGrowthRepositoryImpl implements CompanyGrowthRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CompanyGrowthProjection> findCompanyGrowth(Long companyId) {
        return queryFactory.select(CompanyGrowthProjection.CONSTRUCTOR_EXPRESSION)
                .from(companyGrowth)
                .where(companyGrowth.companyId.eq(companyId))
                .fetch();
    }
}
