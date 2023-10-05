package com.growup.growthmate.company.repository.detail;

import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.company.dto.detail.CompanyDetailRequest;
import com.growup.growthmate.company.dto.detail.CompanySelectRequest;
import com.growup.growthmate.company.dto.detail.CompanySelectResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.growup.growthmate.company.domain.QCompany.company;

@RequiredArgsConstructor
public class CompanyDetailRepositoryImpl implements CompanyDetailRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Company> findAllCompanies(CompanySelectRequest request) {

        return jpaQueryFactory.selectFrom(company)
                .where(getCursorCondition(request.cursor(), company.id))
                .orderBy(company.id.desc())
                .limit(request.size())
                .fetch();
    }

    @Override
    public Company findCompanyDetail(CompanyDetailRequest request) {

        return jpaQueryFactory.selectFrom(company)
                .where(company.id.eq(request.companyId()))
                .fetchOne();
    }

    private BooleanBuilder getCursorCondition(Long cursor, NumberPath<Long> id) {
        if (cursor == null) {
            return new BooleanBuilder();
        }
        return new BooleanBuilder(id.lt(cursor));
    }

}
