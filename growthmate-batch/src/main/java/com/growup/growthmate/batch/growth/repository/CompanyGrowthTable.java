package com.growup.growthmate.batch.growth.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanyGrowthTable {

    public static final String ID = "companyGrowthId";
    public static final String COMPANY_ID = "companyId";
    public static final String YEAR = "year";
    public static final String SALES = "sales";

    public static final String INSERT_SQL = "INSERT INTO company_growth(" +
            "company_id, years, sales" +
            ") VALUES(" +
            ":companyId, :year, :sales" +
            ")";

    public static final String UPDATE_SQL = "UPDATE company_growth SET " +
            "years = :year, " +
            "sales = :sales " +
            "WHERE company_growth_id = :companyGrowthId";
}
