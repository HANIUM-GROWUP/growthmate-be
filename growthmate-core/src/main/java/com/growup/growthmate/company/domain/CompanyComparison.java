package com.growup.growthmate.company.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class CompanyComparison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_comparison_id")
    private Long id;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private Long salesForecast;

    @Column(nullable = false)
    private Double salesForecastPercentage;

    public CompanyComparison(Long companyId, Long salesForecast, Double salesForecastPercentage) {
        this(null, companyId, salesForecast, salesForecastPercentage);
    }
}
