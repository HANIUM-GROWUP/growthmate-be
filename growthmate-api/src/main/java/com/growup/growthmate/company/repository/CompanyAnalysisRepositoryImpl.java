package com.growup.growthmate.company.repository;

import com.growup.growthmate.company.domain.CompanyAnalysis;
import com.growup.growthmate.company.dto.CompanyAnalysisRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.growup.growthmate.company.domain.QCompanyAnalysis.companyAnalysis;

@RequiredArgsConstructor
public class CompanyAnalysisRepositoryImpl implements CompanyAnalysisRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public CompanyAnalysis findCompanyAnalysis(CompanyAnalysisRequest request) {

        return jpaQueryFactory.selectFrom(companyAnalysis)
                .where(companyAnalysis.companyId.eq(request.companyId()))
                .fetchOne();
    }

}
