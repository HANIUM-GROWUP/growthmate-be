package com.growup.growthmate.batch.sentiment.repository;

import com.growup.growthmate.batch.BatchRepository;
import com.growup.growthmate.company.domain.CompanySentiment;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.growup.growthmate.batch.sentiment.repository.CompanySentimentTable.*;

@Component
@RequiredArgsConstructor
public class CompanySentimentBatchRepository implements BatchRepository<CompanySentiment> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void insertAll(List<CompanySentiment> entities) {
        jdbcTemplate.batchUpdate(INSERT_SQL, generateParameterSource(entities));
    }

    @Override
    public void updateAll(List<CompanySentiment> entities) {
        jdbcTemplate.batchUpdate(UPDATE_SQL, generateParameterSource(entities));
    }

    private SqlParameterSource[] generateParameterSource(List<CompanySentiment> sentiments) {
        return sentiments.stream()
                .map(this::mapParameters)
                .toArray(SqlParameterSource[]::new);
    }

    private SqlParameterSource mapParameters(CompanySentiment sentiment) {
        MapSqlParameterSource source = new MapSqlParameterSource(generateSentimentParams(sentiment));
        Optional.ofNullable(sentiment.getId())
                .ifPresent(id -> source.addValue(ID, id));
        return source;
    }

    private Map<String, Object> generateSentimentParams(CompanySentiment sentiment) {
        Map<String, Object> params = new HashMap<>();
        params.put(COMPANY_ID, sentiment.getCompanyId());
        params.put(POSITIVE_RATE, sentiment.getPositiveRate());
        params.put(NEGATIVE_RATE, sentiment.getNegativeRate());
        return params;
    }
}
