package com.growup.growthmate.batch.company.repository;

import com.growup.growthmate.company.domain.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CompanyBatchRepositoryImpl implements CompanyBatchRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void insertAll(List<Company> companies) {

    }

    @Override
    public void updateAll(List<Company> companies) {

    }
}
