package com.growup.growthmate.company.repository.sentiment.projection;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.growup.growthmate.company.domain.QCompanySentiment.companySentiment;

@AllArgsConstructor
@Getter
public class CompanySentimentProjection {

    public static final ConstructorExpression<CompanySentimentProjection> CONSTRUCTOR_EXPRESSION =
            Projections.constructor(CompanySentimentProjection.class,
                    companySentiment.positiveRate,
                    companySentiment.negativeRate
            );

    private Double positiveRate;
    private Double negativeRate;

}
