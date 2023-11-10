package com.growup.growthmate.company.repository.comparison;

import com.growup.growthmate.company.domain.CompanyComparison;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.growup.growthmate.company.domain.QCompanyComparison.companyComparison;

@RequiredArgsConstructor
public class CompanyComparisonRepositoryImpl implements CompanyComparisonRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public CompanyComparison findCompanyComparison(Long companyId) {
        return jpaQueryFactory.selectFrom(companyComparison)
                .where(companyComparison.companyId.eq(companyId))
                .fetchOne();
    }

}
