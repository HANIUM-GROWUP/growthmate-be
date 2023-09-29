package com.growup.growthmate.batch.company.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanyTable {

    public static final String ID = "companyId";
    public static final String NAME = "name";
    public static final String IMAGE_URL = "imageUrl";
    public static final String CEO = "ceo";
    public static final String SCALE = "scale";
    public static final String BUSINESS_TYPE = "businessType";
    public static final String BUSINESS = "business";
    public static final String ESTABLISH_DATE = "establishmentDate";
    public static final String SALES = "sales";
    public static final String EMPLOYEE_NUMBER = "employeeNumber";
    public static final String ADDRESS = "address";

    public static final String INSERT_SQL = "INSERT INTO company(" +
            "name, image_url, ceo, scale, business_type, business, establishment_date, sales, employee_number, address" +
            ") values(" +
            ":name, :imageUrl, :ceo, :scale, :businessType, :business, :establishmentDate, :sales, :employeeNumber, :address" +
            ")";

    public static final String UPDATE_SQL = "UPDATE company SET " +
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
}
