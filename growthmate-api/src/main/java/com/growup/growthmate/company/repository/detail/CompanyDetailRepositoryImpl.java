package com.growup.growthmate.company.repository.detail;

import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.company.dto.detail.CompanyDetailRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.growup.growthmate.company.domain.QCompany.company;

@RequiredArgsConstructor
public class CompanyDetailRepositoryImpl implements CompanyDetailRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Company findCompanyDetail(CompanyDetailRequest request) {

        return jpaQueryFactory.selectFrom(company)
                .where(company.id.eq(request.companyId()))
                .fetchOne();
    }

}
