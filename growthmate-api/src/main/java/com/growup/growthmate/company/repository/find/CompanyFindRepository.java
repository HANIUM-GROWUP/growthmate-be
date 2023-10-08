package com.growup.growthmate.company.repository.find;

import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.company.dto.find.CompanyDetailRequest;
import com.growup.growthmate.company.dto.find.SortedCompanyRequest;

import java.util.List;

public interface CompanyFindRepository {

    List<Company> findSortedCompanies(SortedCompanyRequest request);

    Company findCompanyDetail(CompanyDetailRequest request);

}
