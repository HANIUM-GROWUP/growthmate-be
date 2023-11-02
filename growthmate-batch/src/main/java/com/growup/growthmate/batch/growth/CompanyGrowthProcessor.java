package com.growup.growthmate.batch.growth;

import com.growup.growthmate.batch.company.repository.CompanyExistsRepository;
import com.growup.growthmate.batch.growth.repository.CompanyGrowthRepository;
import com.growup.growthmate.company.domain.CompanyGrowth;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Component
@RequiredArgsConstructor
public class CompanyGrowthProcessor implements ItemProcessor<List<CompanyGrowthDto>, List<CompanyGrowth>> {

    private final CompanyGrowthRepository companyGrowthRepository;
    private final CompanyExistsRepository companyExistsRepository;

    @Override
    public List<CompanyGrowth> process(List<CompanyGrowthDto> items) {
        String name = items.get(0).name();
        return companyExistsRepository.findIdByName(name)
                .map(companyId -> mapToCompanyGrowth(companyId, items))
                .orElse(null);
    }

    private List<CompanyGrowth> mapToCompanyGrowth(Long companyId, List<CompanyGrowthDto> items) {
        List<CompanyGrowth> entities = companyGrowthRepository.findByCompanyId(companyId);
        Map<Integer, Long> groupedEntityIdByYear = groupGrowthIdByYear(entities);
        return items.stream()
                .map(item -> new CompanyGrowth(
                        groupedEntityIdByYear.getOrDefault(item.year(), null),
                        companyId,
                        item.year(),
                        item.sales())
                )
                .toList();
    }

    private Map<Integer, Long> groupGrowthIdByYear(List<CompanyGrowth> entities) {
        return entities.stream()
                .collect(toMap(CompanyGrowth::getYear, CompanyGrowth::getId));
    }
}
