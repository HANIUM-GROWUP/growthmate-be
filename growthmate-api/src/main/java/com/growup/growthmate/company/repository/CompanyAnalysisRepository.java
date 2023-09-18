package com.growup.growthmate.company.repository;

import com.growup.growthmate.company.domain.CompanyAnalysis;
import com.growup.growthmate.company.dto.CompanyAnalysisRequest;

public interface CompanyAnalysisRepository {

    CompanyAnalysis findCompanyAnalysis(CompanyAnalysisRequest request);

}
