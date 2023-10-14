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
public class CompanyGrowth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_growth_id")
    private Long id;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Long sales;

    public CompanyGrowth(Long companyId,
                         Integer year,
                         Long sales) {
        this(null, companyId, year, sales);
    }
}
