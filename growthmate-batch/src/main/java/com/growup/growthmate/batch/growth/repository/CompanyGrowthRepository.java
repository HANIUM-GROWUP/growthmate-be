package com.growup.growthmate.batch.growth.repository;

import com.growup.growthmate.company.domain.CompanyGrowth;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CompanyGrowthRepository extends Repository<CompanyGrowth, Long> {

    List<CompanyGrowth> findByCompanyId(Long companyId);
}
