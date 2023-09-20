package com.growup.growthmate.company.application;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.company.domain.CompanyAnalysis;
import com.growup.growthmate.company.domain.exception.CompanyException;
import com.growup.growthmate.company.dto.CompanyAnalysisRequest;
import com.growup.growthmate.company.dto.CompanyAnalysisResponse;
import com.growup.growthmate.company.mapper.CompanyAnalysisMapper;
import com.growup.growthmate.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyAnalysisMapper companyAnalysisMapper;

    public CompanyAnalysisResponse findCompanyAnalysis(CompanyAnalysisRequest request) {

        Optional<CompanyAnalysis> entityResponse = Optional.ofNullable(companyRepository.findCompanyAnalysis(request));

        return entityResponse
                .map(companyAnalysisMapper::toAnalysisDTO)
                .orElseThrow(() -> new BusinessException(CompanyException.NO_FOUND_COMPANY.getHttpStatusCode(), CompanyException.NO_FOUND_COMPANY.getMessage()));
    }
}
