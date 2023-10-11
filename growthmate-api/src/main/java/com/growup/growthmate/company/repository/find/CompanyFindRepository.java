package com.growup.growthmate.company.repository.find;

import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.company.dto.find.SortedCompanyRequest;

import java.util.List;
import java.util.Optional;

public interface CompanyFindRepository {

    List<Company> findSortedCompanies(SortedCompanyRequest request);

    Optional<Company> findCompanyDetail(Long companyId);

}
