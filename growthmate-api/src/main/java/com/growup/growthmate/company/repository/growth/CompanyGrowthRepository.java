package com.growup.growthmate.company.repository.growth;

import com.growup.growthmate.company.repository.growth.projection.CompanyGrowthProjection;

import java.util.List;

public interface CompanyGrowthRepository {

    List<CompanyGrowthProjection> findCompanyGrowth(Long companyId);

}
