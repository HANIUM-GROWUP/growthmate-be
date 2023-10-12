package com.growup.growthmate.batch.analysis.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanyAnalysisTable {

    public static final String ID = "companyAnalysisId";
    public static final String COMPANY_ID = "companyId";
    public static final String GROWTH = "growth";
    public static final String STABILITY = "stability";
    public static final String PROFITABILITY = "profitability";
    public static final String EFFICIENCY = "efficiency";
    public static final String BUSINESS_PERFORMANCE = "businessPerformance";

    public static final String INSERT_SQL = "INSERT INTO company_analysis(" +
            "company_id, growth, stability, profitability, efficiency, business_performance" +
            ") VALUES(" +
            ":companyId, :growth, :stability, :profitability, :efficiency, :businessPerformance" +
            ")";

    public static final String UPDATE_SQL = "UPDATE company_analysis SET " +
            "growth = :growth, " +
            "stability = :stability, " +
            "profitability = :profitability, " +
            "efficiency = :efficiency, " +
            "business_performance = :businessPerformance " +
            "WHERE company_analysis_id = :companyAnalysisId";
}
