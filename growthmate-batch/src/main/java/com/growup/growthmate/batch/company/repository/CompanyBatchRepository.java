package com.growup.growthmate.batch.company.repository;

import com.growup.growthmate.company.domain.Company;

import java.util.List;

public interface CompanyBatchRepository {

    void insertAll(List<Company> companies);

    void updateAll(List<Company> companies);
}
