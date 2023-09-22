package com.growup.growthmate.company.api;

import com.growup.growthmate.company.application.CompanyService;
import com.growup.growthmate.company.dto.analysis.CompanyAnalysisRequest;
import com.growup.growthmate.company.dto.analysis.CompanyAnalysisResponse;
import com.growup.growthmate.company.dto.detail.CompanyDetailRequest;
import com.growup.growthmate.company.dto.detail.CompanyDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CompanyV1Controller {

    private final CompanyService companyService;

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

}
