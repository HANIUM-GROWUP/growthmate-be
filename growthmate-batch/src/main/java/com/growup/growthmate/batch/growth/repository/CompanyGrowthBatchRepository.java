package com.growup.growthmate.batch.growth.repository;

import com.growup.growthmate.batch.BatchAbstractRepository;
import com.growup.growthmate.company.domain.CompanyGrowth;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Component
public class CompanyGrowthBatchRepository extends BatchAbstractRepository<CompanyGrowth> {

    private static final String ID = "companyGrowthId";
    private static final String COMPANY_ID = "companyId";
    private static final String YEAR = "year";
    private static final String SALES = "sales";

    private static final String INSERT_SQL = "INSERT INTO company_growth(" +
            "company_id, years, sales" +
            ") VALUES(" +
            ":companyId, :year, :sales" +
            ")";

    private static final String UPDATE_SQL = "UPDATE company_growth SET " +
            "years = :year, " +
            "sales = :sales " +
            "WHERE company_growth_id = :companyGrowthId";


    protected CompanyGrowthBatchRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, INSERT_SQL, UPDATE_SQL);
    }

    @Override
    protected Map<String, Object> generateEntityParams(CompanyGrowth growth) {
        Map<String, Object> params = new HashMap<>();
        params.put(COMPANY_ID, growth.getCompanyId());
        params.put(YEAR, growth.getYear());
        params.put(SALES, growth.getSales());
        Optional.ofNullable(growth.getId())
                .ifPresent(id -> params.put(ID, id));
        return params;
    }
}
