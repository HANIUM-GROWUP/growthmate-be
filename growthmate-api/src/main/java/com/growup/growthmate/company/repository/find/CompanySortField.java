package com.growup.growthmate.company.repository.find;

import com.growup.growthmate.company.domain.Company;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import lombok.Getter;

import java.util.Arrays;

import static com.growup.growthmate.company.domain.QCompany.company;

@Getter
public enum CompanySortField {
    ESTABLISHMENT_DATE("establishmentDate", company.establishmentDate) {
        @Override
        public BooleanBuilder getBooleanBuilder(Company cursor) {
            return new BooleanBuilder(company.establishmentDate.loe(cursor.getEstablishmentDate()))
                    .and(company.id.ne(cursor.getId()));
        }
    },
    SALES("sales", company.sales) {
        @Override
        public BooleanBuilder getBooleanBuilder(Company cursor) {
            return new BooleanBuilder(company.sales.loe(cursor.getSales()))
                    .and(company.id.ne(cursor.getId()));
        }
    },
    ID("companyId", company.id) {
        @Override
        public BooleanBuilder getBooleanBuilder(Company cursor) {
            return new BooleanBuilder(company.id.lt(cursor.getId()));
        }
    };

    private final String sort;
    private final ComparableExpressionBase<?> orderExpression;

    CompanySortField(String sort, ComparableExpressionBase<?> orderExpression) {
        this.sort = sort;
        this.orderExpression = orderExpression;
    }

    public static CompanySortField from(String sortValue) {
        return Arrays.stream(values())
                .filter(companySortField -> companySortField.sort.equals(sortValue))
                .findAny()
                .orElse(CompanySortField.ID);

    }

    public abstract BooleanBuilder getBooleanBuilder(Company cursor);
}
