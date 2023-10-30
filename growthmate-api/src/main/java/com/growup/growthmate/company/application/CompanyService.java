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
import com.growup.growthmate.company.dto.growth.CompanyGrowthResponse;
import com.growup.growthmate.company.dto.news.CompanyNewsRequest;
import com.growup.growthmate.company.dto.news.CompanyNewsResponse;
import com.growup.growthmate.company.dto.sentiment.CompanySentimentResponse;
import com.growup.growthmate.company.mapper.CompanyMapper;
import com.growup.growthmate.company.mapper.CompanyNewsMapper;
import com.growup.growthmate.company.mapper.SortedCompanyMapper;
import com.growup.growthmate.company.repository.CompanyRepository;
import com.growup.growthmate.company.repository.growth.projection.CompanyGrowthProjection;
import com.growup.growthmate.company.repository.news.projection.CompanyNewsProjection;
import com.growup.growthmate.company.repository.sentiment.projection.CompanySentimentProjection;
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
    private final SortedCompanyMapper sortedCompanyMapper;
    private final CompanyNewsMapper companyNewsMapper;

    public List<SortedCompanyResponse> findSortedCompanies(SortedCompanyRequest request) {
        List<Company> companies = companyRepository.findSortedCompanies(request);

        return companies.stream()
                .map(sortedCompanyMapper::toResponse)
                .toList();
    }

    public CompanyDetailResponse findCompanyDetail(CompanyDetailRequest request) {

        Optional<Company> entityResponse = companyRepository.findCompanyDetail(request.companyId());

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

    public List<CompanyGrowthResponse> findCompanyGrowth(Long companyId) {

        if (companyId == null) throw throwCompanyNotFoundException();
        List<CompanyGrowthProjection> entityResponse = companyRepository.findCompanyGrowth(companyId);

        return companyMapper.toGrowthDTO(entityResponse);
    }

    public CompanySentimentResponse findCompanySentiment(Long companyId) {

        if (companyId == null) throw throwCompanyNotFoundException();
        CompanySentimentProjection entityResponse = companyRepository.findCompanySentiment(companyId);

        return companyMapper.toSentimentDTO(entityResponse);
    }

    public List<CompanyNewsResponse> findCompanyNewsList(CompanyNewsRequest request) {

        if (request.companyId() == null) throw throwCompanyNotFoundException();
        List<CompanyNewsProjection> entityResponse = companyRepository.findCompanyNewsList(request);

        return entityResponse.stream()
                .map(companyNewsMapper::toResponse)
                .toList();
    }

    private BusinessException throwCompanyNotFoundException() {

        CompanyException notFound = CompanyException.NO_FOUND_COMPANY;
        return new BusinessException(notFound.getHttpStatusCode(), notFound.getMessage());
    }
}
