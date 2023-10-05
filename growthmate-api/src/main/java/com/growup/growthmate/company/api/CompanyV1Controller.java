package com.growup.growthmate.company.api;

import com.growup.growthmate.company.application.CompanyService;
import com.growup.growthmate.company.dto.analysis.CompanyAnalysisRequest;
import com.growup.growthmate.company.dto.analysis.CompanyAnalysisResponse;
import com.growup.growthmate.company.dto.detail.CompanyDetailRequest;
import com.growup.growthmate.company.dto.detail.CompanyDetailResponse;
import com.growup.growthmate.company.dto.detail.CompanySelectRequest;
import com.growup.growthmate.company.dto.detail.CompanySelectResponse;
import com.growup.growthmate.query.dto.PagingParams;
import com.growup.growthmate.query.dto.request.PostPreviewRequest;
import com.growup.growthmate.query.dto.response.PostPreviewResponse;
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
    public ResponseEntity<List<CompanySelectResponse>> findAllCompanies(@ModelAttribute PagingParams params) {

        CompanySelectRequest request = new CompanySelectRequest(params.getCursor(), params.getSize());
        List<CompanySelectResponse> responses = companyService.findAllCompanies(request);

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

}
