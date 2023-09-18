package com.growup.growthmate.company.api;

import com.growup.growthmate.company.application.CompanyService;
import com.growup.growthmate.company.dto.CompanyAnalysisRequest;
import com.growup.growthmate.company.dto.CompanyAnalysisResponse;
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

    @GetMapping(value = "companies/{comapany_id}/analyze")
    public ResponseEntity<CompanyAnalysisResponse> findCompanyAnalysis(
            @PathVariable Long comapany_id
    ) {

        CompanyAnalysisRequest request = new CompanyAnalysisRequest(comapany_id);
        CompanyAnalysisResponse response = companyService.findCompanyAnalysis(request);

        return ResponseEntity.ok(response);
    }

}