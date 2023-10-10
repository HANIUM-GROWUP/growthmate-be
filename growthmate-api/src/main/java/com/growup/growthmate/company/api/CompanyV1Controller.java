package com.growup.growthmate.company.api;

import com.growup.growthmate.company.application.CompanyService;
import com.growup.growthmate.company.dto.analysis.CompanyAnalysisRequest;
import com.growup.growthmate.company.dto.analysis.CompanyAnalysisResponse;
import com.growup.growthmate.company.dto.find.CompanyDetailRequest;
import com.growup.growthmate.company.dto.find.CompanyDetailResponse;
import com.growup.growthmate.company.dto.find.SortedCompanyRequest;
import com.growup.growthmate.company.dto.find.SortedCompanyResponse;
import com.growup.growthmate.query.dto.PagingParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

}
