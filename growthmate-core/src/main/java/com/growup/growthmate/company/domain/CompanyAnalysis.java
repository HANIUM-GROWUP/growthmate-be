package com.growup.growthmate.company.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CompanyAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_analysis_id")
    private Long id;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private Double growth;

    @Column(nullable = false)
    private Double efficiency;

    @Column(nullable = false)
    private Double profitability;

    @Column(nullable = false)
    private Double technology;

    @Column(nullable = false)
    private Double financialStability;
}
