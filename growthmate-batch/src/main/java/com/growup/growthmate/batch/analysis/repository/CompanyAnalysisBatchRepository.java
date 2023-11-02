package com.growup.growthmate.batch.analysis.repository;

import com.growup.growthmate.batch.BatchAbstractRepository;
import com.growup.growthmate.company.domain.CompanyAnalysis;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class CompanyAnalysisBatchRepository extends BatchAbstractRepository<CompanyAnalysis> {

    private static final String ID = "companyAnalysisId";
    private static final String COMPANY_ID = "companyId";
    private static final String GROWTH = "growth";
    private static final String STABILITY = "stability";
    private static final String PROFITABILITY = "profitability";
    private static final String EFFICIENCY = "efficiency";
    private static final String BUSINESS_PERFORMANCE = "businessPerformance";

    private static final String INSERT_SQL = "INSERT INTO company_analysis(" +
            "company_id, growth, stability, profitability, efficiency, business_performance" +
            ") VALUES(" +
            ":companyId, :growth, :stability, :profitability, :efficiency, :businessPerformance" +
            ")";

    private static final String UPDATE_SQL = "UPDATE company_analysis SET " +
            "growth = :growth, " +
            "stability = :stability, " +
            "profitability = :profitability, " +
            "efficiency = :efficiency, " +
            "business_performance = :businessPerformance " +
            "WHERE company_analysis_id = :companyAnalysisId";


    protected CompanyAnalysisBatchRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, INSERT_SQL, UPDATE_SQL);
    }

    @Override
    protected Map<String, Object> generateEntityParams(CompanyAnalysis analysis) {
        Map<String, Object> params = new HashMap<>();
        params.put(COMPANY_ID, analysis.getCompanyId());
        params.put(GROWTH, analysis.getGrowth());
        params.put(STABILITY, analysis.getStability());
        params.put(PROFITABILITY, analysis.getProfitability());
        params.put(EFFICIENCY, analysis.getEfficiency());
        params.put(BUSINESS_PERFORMANCE, analysis.getBusinessPerformance());
        Optional.ofNullable(analysis.getId())
                .ifPresent(id -> params.put(ID, id));
        return params;
    }
}
