package com.growup.growthmate.company.repository.detail;

import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.company.dto.detail.CompanyDetailRequest;
import com.growup.growthmate.company.dto.detail.CompanySelectRequest;

import java.util.List;

public interface CompanyFindRepository {

    List<Company> findSortedCompanies(CompanySelectRequest request);

    Company findCompanyDetail(CompanyDetailRequest request);

}
