package com.growup.growthmate.batch.company.repository;

import com.growup.growthmate.batch.BatchRepository;
import com.growup.growthmate.company.domain.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.growup.growthmate.batch.company.repository.CompanyTable.*;

@Component
@RequiredArgsConstructor
public class CompanyBatchRepository implements BatchRepository<Company> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void insertAll(List<Company> companies) {
        jdbcTemplate.batchUpdate(INSERT_SQL, generateParameterSource(companies));
    }

    @Override
    public void updateAll(List<Company> companies) {
        jdbcTemplate.batchUpdate(UPDATE_SQL, generateParameterSource(companies));
    }

    private SqlParameterSource[] generateParameterSource(List<Company> companies) {
        return companies.stream()
                .map(this::mapParameters)
                .toArray(SqlParameterSource[]::new);
    }

    private SqlParameterSource mapParameters(Company company) {
        MapSqlParameterSource source = new MapSqlParameterSource(generateCompanyParams(company));
        Optional.ofNullable(company.getId())
                .ifPresent(id -> source.addValue(ID, id));
        return source;
    }

    private Map<String, Object> generateCompanyParams(Company company) {
        Map<String, Object> params = new HashMap<>();
        params.put(NAME, company.getName());
        params.put(IMAGE_URL, company.getImageUrl());
        params.put(CEO, company.getCeo());
        params.put(SCALE, company.getScale());
        params.put(BUSINESS_TYPE, company.getBusinessType());
        params.put(BUSINESS, company.getBusiness());
        params.put(ESTABLISH_DATE, company.getEstablishmentDate());
        params.put(SALES, company.getSales());
        params.put(EMPLOYEE_NUMBER, company.getEmployeeNumber());
        params.put(ADDRESS, company.getAddress());
        return params;
    }
}
