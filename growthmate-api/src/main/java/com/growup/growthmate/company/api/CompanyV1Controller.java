package com.growup.growthmate.company.api;

import com.growup.growthmate.company.application.CompanyService;
import com.growup.growthmate.company.dto.analysis.CompanyAnalysisRequest;
import com.growup.growthmate.company.dto.analysis.CompanyAnalysisResponse;
import com.growup.growthmate.company.dto.comparison.CompanyComparisonResponse;
import com.growup.growthmate.company.dto.find.CompanyDetailRequest;
import com.growup.growthmate.company.dto.find.CompanyDetailResponse;
import com.growup.growthmate.company.dto.find.SortedCompanyRequest;
import com.growup.growthmate.company.dto.find.SortedCompanyResponse;
import com.growup.growthmate.company.dto.growth.CompanyGrowthResponse;
import com.growup.growthmate.company.dto.news.CompanyNewsRequest;
import com.growup.growthmate.company.dto.news.CompanyNewsResponse;
import com.growup.growthmate.company.dto.sentiment.CompanySentimentResponse;
import com.growup.growthmate.query.dto.PagingParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CompanyV1Controller {

    private final CompanyService companyService;

    @GetMapping(value = "companies")
    public ResponseEntity<List<SortedCompanyResponse>> findAllCompanies(
            @ModelAttribute PagingParams params,
            @RequestParam(defaultValue = "companyId") String sort) {

        SortedCompanyRequest request = new SortedCompanyRequest(params.getCursor(), params.getSize(), sort);
        List<SortedCompanyResponse> responses = companyService.findSortedCompanies(request);

        return ResponseEntity.ok(responses);
    }

    @GetMapping(value = "companies/{companyId}")
    public ResponseEntity<CompanyDetailResponse> findCompanyDetail(
            @PathVariable Long companyId) {

        CompanyDetailRequest request = new CompanyDetailRequest(companyId);
        CompanyDetailResponse response = companyService.findCompanyDetail(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "companies/{companyId}/analyze")
    public ResponseEntity<CompanyAnalysisResponse> findCompanyAnalysis(
            @PathVariable Long companyId) {

        CompanyAnalysisRequest request = new CompanyAnalysisRequest(companyId);
        CompanyAnalysisResponse response = companyService.findCompanyAnalysis(request);

        return ResponseEntity.ok(response);
    }

    /**
     * 기업 성장 그래프
     *
     * @param companyId
     * @return CompanyGrowthResponse
     */
    @GetMapping(value = "companies/{companyId}/growth")
    public ResponseEntity<List<CompanyGrowthResponse>> findCompanyGrowth(
            @PathVariable Long companyId) {

        List<CompanyGrowthResponse> response = companyService.findCompanyGrowth(companyId);

        return ResponseEntity.ok(response);
    }

    /**
     * 언론 감정 분석
     */
    @GetMapping(value = "/companies/{companyId}/sentiment")
    public ResponseEntity<CompanySentimentResponse> findCompanySentiment(
            @PathVariable Long companyId) {

        CompanySentimentResponse response = companyService.findCompanySentiment(companyId);

        return ResponseEntity.ok(response);
    }

    /**
     * 언론 긍부정 뉴스 목록
     */
    @GetMapping(value = "/companies/{companyId}/news")
    public ResponseEntity<List<CompanyNewsResponse>> findCompanyNewsList(
            @PathVariable Long companyId,
            @ModelAttribute PagingParams params) {

        CompanyNewsRequest request = new CompanyNewsRequest(companyId, params.getCursor(), params.getSize());
        List<CompanyNewsResponse> response = companyService.findCompanyNewsList(request);

        return ResponseEntity.ok(response);
    }

    /**
     * 동종 업계 비교 api
     */
    @GetMapping(value = "/companies/{comapanyId}/comparison")
    public ResponseEntity<CompanyComparisonResponse> findCompanyComparison(
            @PathVariable Long comapanyId) {

        CompanyComparisonResponse response = companyService.findCompanyComparison(comapanyId);
        return ResponseEntity.ok(response);
    }

}
