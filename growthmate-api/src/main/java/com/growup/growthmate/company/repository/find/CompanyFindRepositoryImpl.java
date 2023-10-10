package com.growup.growthmate.company.repository.find;

import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.company.dto.find.SortedCompanyRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.growup.growthmate.company.domain.QCompany.company;

@RequiredArgsConstructor
public class CompanyFindRepositoryImpl implements CompanyFindRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Company> findSortedCompanies(SortedCompanyRequest request) {
        CompanySortField sortField = CompanySortField.from(request.sort());
        BooleanBuilder cursorCondition = getCursorCondition(sortField, request.cursor());
        return jpaQueryFactory.selectFrom(company)
                .where(cursorCondition)
                .orderBy(sortField.getOrderExpression().desc())
                .limit(request.size())
                .fetch();
    }

    private BooleanBuilder getCursorCondition(CompanySortField sortField, Long cursor) {
        return findCompanyDetail(cursor)
                .map(sortField::getBooleanBuilder)
                .orElse(new BooleanBuilder());
    }

    @Override
    public Optional<Company> findCompanyDetail(Long companyId) {
        if (companyId == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(company)
                        .where(company.id.eq(companyId))
                        .fetchOne()
        );
    }
}
