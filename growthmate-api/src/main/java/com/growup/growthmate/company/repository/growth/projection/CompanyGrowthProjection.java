package com.growup.growthmate.company.repository.growth.projection;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.growup.growthmate.company.domain.QCompanyGrowth.companyGrowth;

@AllArgsConstructor
@Getter
public class CompanyGrowthProjection {

    public static final ConstructorExpression<CompanyGrowthProjection> CONSTRUCTOR_EXPRESSION =
            Projections.constructor(CompanyGrowthProjection.class,
                    companyGrowth.year,
                    companyGrowth.sales
            );

    private Integer year;
    private Double sales;
}
