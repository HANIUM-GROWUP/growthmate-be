package com.growup.growthmate.batch.company.repository;

import com.growup.growthmate.batch.BatchAbstractRepository;
import com.growup.growthmate.company.domain.Company;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class CompanyBatchRepository extends BatchAbstractRepository<Company> {

    private static final String ID = "companyId";
    private static final String NAME = "name";
    private static final String IMAGE_URL = "imageUrl";
    private static final String CEO = "ceo";
    private static final String SCALE = "scale";
    private static final String BUSINESS_TYPE = "businessType";
    private static final String BUSINESS = "business";
    private static final String ESTABLISH_DATE = "establishmentDate";
    private static final String SALES = "sales";
    private static final String EMPLOYEE_NUMBER = "employeeNumber";
    private static final String ADDRESS = "address";

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


    protected CompanyBatchRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, INSERT_SQL, UPDATE_SQL);
    }

    @Override
    protected Map<String, Object> generateEntityParams(Company company) {
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
        Optional.ofNullable(company.getId())
                .ifPresent(id -> params.put(ID, id));
        return params;
    }
}
