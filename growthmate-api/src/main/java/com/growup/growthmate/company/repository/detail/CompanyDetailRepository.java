package com.growup.growthmate.company.repository.detail;

import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.company.dto.detail.CompanyDetailRequest;

public interface CompanyDetailRepository {

    Company findCompanyDetail(CompanyDetailRequest request);

}
