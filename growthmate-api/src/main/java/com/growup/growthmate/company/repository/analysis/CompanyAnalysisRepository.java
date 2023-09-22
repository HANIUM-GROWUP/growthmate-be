package com.growup.growthmate.company.repository.analysis;

import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.company.domain.CompanyAnalysis;
import com.growup.growthmate.company.dto.analysis.CompanyAnalysisRequest;
import com.growup.growthmate.company.dto.detail.CompanyDetailRequest;

public interface CompanyAnalysisRepository {

    CompanyAnalysis findCompanyAnalysis(CompanyAnalysisRequest request);

}
