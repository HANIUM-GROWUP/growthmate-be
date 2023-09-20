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
    private Integer growth;

    @Column(nullable = false)
    private Integer stability;

    @Column(nullable = false)
    private Integer profitability;

    @Column(nullable = false)
    private Integer efficiency;

    @Column(nullable = false)
    private Integer businessPerformance;
}
