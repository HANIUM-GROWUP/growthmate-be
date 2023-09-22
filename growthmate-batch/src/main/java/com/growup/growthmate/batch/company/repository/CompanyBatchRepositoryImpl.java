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

@Component
@RequiredArgsConstructor
public class CompanyBatchRepositoryImpl implements CompanyBatchRepository {

    private static final String INSERT_SQL = "INSERT INTO company(" +
            "name, image_url, ceo, scale, business_type, business, establishment_date, sales, employee_number, address" +
            ") values(" +
            ":name, :imageUrl, :ceo, :scale, :businessType, :business, :establishmentDate, :sales, :employeeNumber, :address" +
            ")";

    private static final String UPDATE_SQL = "UPDATE company SET " +
            "name = :name, " +
            "image_url = :imageUrl, " +
            "ceo = :ceo, " +
            "scale = :scale, " +
            "business_type = :businessType, " +
            "business = :business, " +
            "establishment_date = :establishmentDate, " +
            "sales = :sales, " +
            "employee_number = :employeeNumber, " +
            "address = :address " +
            "WHERE company_id = :companyId";

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
                "name", company.getName(),
                "imageUrl", company.getImageUrl(),
                "ceo", company.getCeo(),
                "scale", company.getScale(),
                "businessType", company.getBusinessType(),
                "business", company.getBusiness(),
                "establishmentDate", company.getEstablishmentDate(),
                "sales", company.getSales(),
                "employeeNumber", company.getEmployeeNumber(),
                "address", company.getAddress()
        ));
        Optional.ofNullable(company.getId())
                .ifPresent(id -> source.addValue("companyId", id));
        return source;
    }
}
