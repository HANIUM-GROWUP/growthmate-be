package com.growup.growthmate.batch.analysis;

import com.growup.growthmate.batch.analysis.repository.CompanyAnalysisExistsRepository;
import com.growup.growthmate.batch.company.repository.CompanyExistsRepository;
import com.growup.growthmate.company.domain.CompanyAnalysis;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyAnalysisProcessor implements ItemProcessor<CompanyAnalysisDto, CompanyAnalysis> {

    private final CompanyAnalysisExistsRepository companyAnalysisExistsRepository;
    private final CompanyExistsRepository companyExistsRepository;

    @Override
    public CompanyAnalysis process(CompanyAnalysisDto item) {
        return companyExistsRepository.findIdByName(item.name())
                .map(companyId -> maptoCompanyAnalysis(companyId, item))
                .orElse(null);
    }

    private CompanyAnalysis maptoCompanyAnalysis(Long companyId, CompanyAnalysisDto item) {
        return companyAnalysisExistsRepository.findIdByCompanyId(companyId)
                .map(id -> withId(id, companyId, item))
                .orElse(withoutId(companyId, item));
    }

    private CompanyAnalysis withId(Long id, Long companyId, CompanyAnalysisDto dto) {
        return new CompanyAnalysis(
                id, companyId, dto.growth(), dto.stability(), dto.profitability(), dto.efficiency(), dto.businessPerformance()
        );
    }

    private CompanyAnalysis withoutId(Long companyId, CompanyAnalysisDto dto) {
        return new CompanyAnalysis(
                companyId, dto.growth(), dto.stability(), dto.profitability(), dto.efficiency(), dto.businessPerformance()
        );
    }
}
