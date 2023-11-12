package com.growup.growthmate.batch.comparison;

import com.growup.growthmate.batch.company.repository.CompanyExistsRepository;
import com.growup.growthmate.batch.comparison.repository.CompanyComparisonExistsRepository;
import com.growup.growthmate.company.domain.CompanyComparison;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyComparisonProcessor implements ItemProcessor<CompanyComparisonDto, CompanyComparison> {

    private final CompanyComparisonExistsRepository companyComparisonExistsRepository;
    private final CompanyExistsRepository companyExistsRepository;
    @Override
    public CompanyComparison process(CompanyComparisonDto item) {
        return companyExistsRepository.findIdByName(item.name())
                .map(companyId -> mapToCompanyComparison(companyId, item))
                .orElse(null);
    }

    private CompanyComparison mapToCompanyComparison(Long companyId, CompanyComparisonDto item) {
        Double salesForecastPercentage = item.salesForecastPercentage();
        Long salesForecast = item.salesForecast();
        return companyComparisonExistsRepository.findIdByCompanyId(companyId)
                .map(id -> new CompanyComparison(id, companyId, salesForecast, salesForecastPercentage))
                .orElse(new CompanyComparison(companyId, salesForecast, salesForecastPercentage));
    }
}
