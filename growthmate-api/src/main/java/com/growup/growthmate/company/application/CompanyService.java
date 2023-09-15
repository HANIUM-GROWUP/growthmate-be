package com.growup.growthmate.company.application;

import com.growup.growthmate.company.domain.CompanyAnalysis;
import com.growup.growthmate.company.dto.CompanyAnalysisRequest;
import com.growup.growthmate.company.dto.CompanyAnalysisResponse;
import com.growup.growthmate.company.mapper.CompanyAnalysisMapper;
import com.growup.growthmate.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyAnalysisMapper companyAnalysisMapper;

    public CompanyAnalysisResponse findCompanyAnalysis(CompanyAnalysisRequest request) {

        CompanyAnalysis entityResponse = companyRepository.findCompanyAnalysis(request);
        CompanyAnalysisResponse response = companyAnalysisMapper.toAnalysisDTO(entityResponse);

        return response;
    }
}
