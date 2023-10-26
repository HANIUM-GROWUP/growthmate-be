package com.growup.growthmate.batch.growth.repository;

import com.growup.growthmate.batch.BatchRepository;
import com.growup.growthmate.company.domain.CompanyGrowth;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.growup.growthmate.batch.growth.repository.CompanyGrowthTable.*;


@Component
@RequiredArgsConstructor
public class CompanyGrowthBatchRepository implements BatchRepository<CompanyGrowth> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void insertAll(List<CompanyGrowth> entities) {
        jdbcTemplate.batchUpdate(INSERT_SQL, generateParameterSource(entities));
    }

    @Override
    public void updateAll(List<CompanyGrowth> entities) {
        jdbcTemplate.batchUpdate(UPDATE_SQL, generateParameterSource(entities));
    }

    private SqlParameterSource[] generateParameterSource(List<CompanyGrowth> growth) {
        return growth.stream()
                .map(this::mapParameters)
                .toArray(SqlParameterSource[]::new);
    }

    private SqlParameterSource mapParameters(CompanyGrowth growth) {
        MapSqlParameterSource source = new MapSqlParameterSource(generateGrowthParams(growth));
        Optional.ofNullable(growth.getId())
                .ifPresent(id -> source.addValue(ID, id));
        return source;
    }

    private Map<String, Object> generateGrowthParams(CompanyGrowth growth) {
        Map<String, Object> params = new HashMap<>();
        params.put(COMPANY_ID, growth.getCompanyId());
        params.put(YEAR, growth.getYear());
        params.put(SALES, growth.getSales());
        return params;
    }
}
