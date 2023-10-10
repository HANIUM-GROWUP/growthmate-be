package com.growup.growthmate.company.application;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.company.domain.CompanyAnalysis;
import com.growup.growthmate.company.domain.exception.CompanyException;
import com.growup.growthmate.company.dto.analysis.CompanyAnalysisRequest;
import com.growup.growthmate.company.dto.analysis.CompanyAnalysisResponse;
import com.growup.growthmate.company.dto.find.CompanyDetailRequest;
import com.growup.growthmate.company.dto.find.CompanyDetailResponse;
import com.growup.growthmate.company.dto.find.SortedCompanyRequest;
import com.growup.growthmate.company.dto.find.SortedCompanyResponse;
import com.growup.growthmate.company.mapper.CompanyMapper;
import com.growup.growthmate.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public List<SortedCompanyResponse> findSortedCompanies(SortedCompanyRequest request) {
        List<Company> companies = companyRepository.findSortedCompanies(request);

        return companyMapper.toAllSelectDTO(companies);
    }

    public CompanyDetailResponse findCompanyDetail(CompanyDetailRequest request) {

        Optional<Company> entityResponse = Optional.ofNullable(companyRepository.findCompanyDetail(request));

        return entityResponse
                .map(companyMapper::toDetailDTO)
                .orElseThrow(() -> new BusinessException(CompanyException.NO_FOUND_COMPANY.getHttpStatusCode(), CompanyException.NO_FOUND_COMPANY.getMessage()));
    }

    public CompanyAnalysisResponse findCompanyAnalysis(CompanyAnalysisRequest request) {

        Optional<CompanyAnalysis> entityResponse = Optional.ofNullable(companyRepository.findCompanyAnalysis(request));

        return entityResponse
                .map(companyMapper::toAnalysisDTO)
                .orElseThrow(() -> new BusinessException(CompanyException.NO_FOUND_COMPANY.getHttpStatusCode(), CompanyException.NO_FOUND_COMPANY.getMessage()));
    }
}
