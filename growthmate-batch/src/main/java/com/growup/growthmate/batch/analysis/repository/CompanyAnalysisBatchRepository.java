package com.growup.growthmate.batch.analysis.repository;

import com.growup.growthmate.batch.BatchRepository;
import com.growup.growthmate.company.domain.CompanyAnalysis;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.growup.growthmate.batch.analysis.repository.CompanyAnalysisTable.*;

@Component
@RequiredArgsConstructor
public class CompanyAnalysisBatchRepository implements BatchRepository<CompanyAnalysis> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void insertAll(List<CompanyAnalysis> entities) {
        jdbcTemplate.batchUpdate(INSERT_SQL, generateParameterSource(entities));
    }

    @Override
    public void updateAll(List<CompanyAnalysis> entities) {
        jdbcTemplate.batchUpdate(UPDATE_SQL, generateParameterSource(entities));
    }

    private SqlParameterSource[] generateParameterSource(List<CompanyAnalysis> analyses) {
        return analyses.stream()
                .map(this::mapParameters)
                .toArray(SqlParameterSource[]::new);
    }

    private SqlParameterSource mapParameters(CompanyAnalysis analysis) {
        MapSqlParameterSource source = new MapSqlParameterSource(generateAnalysesParams(analysis));
        Optional.ofNullable(analysis.getId())
                .ifPresent(id -> source.addValue(ID, id));
        return source;
    }

    private Map<String, Object> generateAnalysesParams(CompanyAnalysis analysis) {
        Map<String, Object> params = new HashMap<>();
        params.put(COMPANY_ID, analysis.getCompanyId());
        params.put(GROWTH, analysis.getGrowth());
        params.put(STABILITY, analysis.getStability());
        params.put(PROFITABILITY, analysis.getProfitability());
        params.put(EFFICIENCY, analysis.getEfficiency());
        params.put(BUSINESS_PERFORMANCE, analysis.getBusinessPerformance());
        return params;
    }
}
