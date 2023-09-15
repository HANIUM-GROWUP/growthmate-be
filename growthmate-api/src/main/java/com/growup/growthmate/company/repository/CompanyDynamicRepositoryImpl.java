package com.growup.growthmate.company.repository;

import com.growup.growthmate.company.domain.CompanyAnalysis;
import com.growup.growthmate.company.dto.CompanyAnalysisRequest;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.growup.growthmate.company.domain.QCompanyAnalysis.companyAnalysis;

@RequiredArgsConstructor
public class CompanyDynamicRepositoryImpl implements CompanyDynamicRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public CompanyAnalysis findCompanyAnalysis(CompanyAnalysisRequest request) {

        return jpaQueryFactory.select(Projections.bean(CompanyAnalysis.class,
                        companyAnalysis.growth, companyAnalysis.stability, companyAnalysis.profitability,
                        companyAnalysis.efficiency, companyAnalysis.businessPerformance))
                .from(companyAnalysis)
                .where(companyAnalysis.companyId.eq(request.companyId()))
                .fetchOne();
    }

}
