package com.growup.growthmate.company.repository.comparison;

import com.growup.growthmate.company.domain.CompanyComparison;

public interface CompanyComparisonRepository {

    CompanyComparison findCompanyComparison(Long companyId);

}
