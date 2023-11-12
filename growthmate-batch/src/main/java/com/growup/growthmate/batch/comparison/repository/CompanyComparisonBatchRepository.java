package com.growup.growthmate.batch.comparison.repository;

import com.growup.growthmate.batch.BatchAbstractRepository;
import com.growup.growthmate.company.domain.CompanyComparison;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class CompanyComparisonBatchRepository extends BatchAbstractRepository<CompanyComparison> {

    private static final String ID = "companyComparisonId";
    private static final String COMPANY_ID = "companyId";
    private static final String SALES_FORECAST = "salesForecast";
    private static final String SALES_FORECAST_PERCENTAGE = "salesForecastPercentage";

    private static final String INSERT_SQL = "INSERT INTO company_comparison(" +
            "company_id, sales_forecast, sales_forecast_percentage" +
            ") VALUES(" +
            ":companyId, :salesForecast, :salesForecastPercentage" +
            ")";

    private static final String UPDATE_SQL = "UPDATE company_comparison SET " +
            "sales_forecast = :salesForecast, " +
            "sales_forecast_percentage = :salesForecastPercentage " +
            "WHERE company_comparison_id = :companyComparisonId";


    protected CompanyComparisonBatchRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, INSERT_SQL, UPDATE_SQL);
    }

    @Override
    protected Map<String, Object> generateEntityParams(CompanyComparison comparison) {
        Map<String, Object> params = new HashMap<>();
        params.put(COMPANY_ID, comparison.getCompanyId());
        params.put(SALES_FORECAST, comparison.getSalesForecast());
        params.put(SALES_FORECAST_PERCENTAGE, comparison.getSalesForecastPercentage());
        Optional.ofNullable(comparison.getId())
                .ifPresent(id -> params.put(ID, id));
        return params;
    }
}
