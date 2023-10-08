package com.growup.growthmate.company.repository.find;

import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.company.dto.find.CompanyDetailRequest;
import com.growup.growthmate.company.dto.find.SortField;
import com.growup.growthmate.company.dto.find.SortedCompanyRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.growup.growthmate.company.domain.QCompany.company;
import static com.growup.growthmate.company.dto.find.SortField.ESTABLISHMENT_DATE;
import static com.growup.growthmate.company.dto.find.SortField.SALES;

@RequiredArgsConstructor
public class CompanyFindRepositoryImpl implements CompanyFindRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Company> findSortedCompanies(SortedCompanyRequest request) {

        ComparableExpressionBase<?> orderByField;

        SortField sortField;
        try {
            sortField = SortField.valueOf(request.sort().toUpperCase());
        } catch (IllegalArgumentException e) {
            sortField = SortField.ID; // 기본값 설정
        }

        switch (sortField) {
            case ESTABLISHMENT_DATE:
                orderByField = company.establishmentDate;
                break;
            case SALES:
                orderByField = company.sales;
                break;
            default:
                orderByField = company.id;
                break;
        }

        return jpaQueryFactory.selectFrom(company)
                .where(getCursorCondition(request.cursor(), company.id))
                .orderBy(orderByField.desc())
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
