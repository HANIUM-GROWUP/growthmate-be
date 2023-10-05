package com.growup.growthmate.company.application;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.company.domain.CompanyAnalysis;
import com.growup.growthmate.company.domain.exception.CompanyException;
import com.growup.growthmate.company.dto.analysis.CompanyAnalysisRequest;
import com.growup.growthmate.company.dto.analysis.CompanyAnalysisResponse;
import com.growup.growthmate.company.dto.detail.CompanyDetailRequest;
import com.growup.growthmate.company.dto.detail.CompanyDetailResponse;
import com.growup.growthmate.company.dto.detail.CompanySelectRequest;
import com.growup.growthmate.company.dto.detail.CompanySelectResponse;
import com.growup.growthmate.company.mapper.CompanyAnalysisMapper;
import com.growup.growthmate.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyAnalysisMapper companyAnalysisMapper;

    public List<CompanySelectResponse> findAllCompanies(CompanySelectRequest request) {
        List<Company> companies = companyRepository.findAllCompanies(request);

        if (companies.isEmpty()) {
            throw new BusinessException(CompanyException.NO_FOUND_COMPANY.getHttpStatusCode(), CompanyException.NO_FOUND_COMPANY.getMessage());
        }

        return companyAnalysisMapper.toAllSelectDTO(companies);
    }

    public CompanyDetailResponse findCompanyDetail(CompanyDetailRequest request) {

        Optional<Company> entityResponse = Optional.ofNullable(companyRepository.findCompanyDetail(request));

        return entityResponse
                .map(companyAnalysisMapper::toDetailDTO)
                .orElseThrow(() -> new BusinessException(CompanyException.NO_FOUND_COMPANY.getHttpStatusCode(), CompanyException.NO_FOUND_COMPANY.getMessage()));
    }

    public CompanyAnalysisResponse findCompanyAnalysis(CompanyAnalysisRequest request) {

        Optional<CompanyAnalysis> entityResponse = Optional.ofNullable(companyRepository.findCompanyAnalysis(request));

        return entityResponse
                .map(companyAnalysisMapper::toAnalysisDTO)
                .orElseThrow(() -> new BusinessException(CompanyException.NO_FOUND_COMPANY.getHttpStatusCode(), CompanyException.NO_FOUND_COMPANY.getMessage()));
    }
}
