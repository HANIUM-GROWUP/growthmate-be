package com.growup.growthmate.batch.company.repository;

import com.growup.growthmate.company.domain.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.growup.growthmate.batch.company.repository.CompanyTable.*;

@Component
@RequiredArgsConstructor
public class CompanyBatchRepositoryImpl implements CompanyBatchRepository {

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
        MapSqlParameterSource source = new MapSqlParameterSource(Map.of(
                NAME, company.getName(),
                IMAGE_URL, company.getImageUrl(),
                CEO, company.getCeo(),
                SCALE, company.getScale(),
                BUSINESS_TYPE, company.getBusinessType(),
                BUSINESS, company.getBusiness(),
                ESTABLISH_DATE, company.getEstablishmentDate(),
                SALES, company.getSales(),
                EMPLOYEE_NUMBER, company.getEmployeeNumber(),
                ADDRESS, company.getAddress()
        ));
        Optional.ofNullable(company.getId())
                .ifPresent(id -> source.addValue(ID, id));
        return source;
    }
}
