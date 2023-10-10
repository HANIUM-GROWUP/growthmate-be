package com.growup.growthmate.company.repository.find;

import com.querydsl.core.types.dsl.ComparableExpressionBase;
import lombok.Getter;

import java.util.Arrays;

import static com.growup.growthmate.company.domain.QCompany.company;

@Getter
public enum CompanySortField {
    ESTABLISHMENT_DATE("establishmentDate", company.establishmentDate),
    SALES("sales", company.sales),
    ID("companyId", company.id);

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

}
