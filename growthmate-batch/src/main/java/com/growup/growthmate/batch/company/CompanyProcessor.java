package com.growup.growthmate.batch.company;

import com.growup.growthmate.batch.company.repository.CompanyExistsRepository;
import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.support.log.ExecutionTimeLog;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyProcessor implements ItemProcessor<Company, Company> {

    private final CompanyExistsRepository companyExistsRepository;

    @Override
    @ExecutionTimeLog
    public Company process(Company item) {
        return companyExistsRepository.findIdByName(item.getName())
                .map(item::withId)
                .orElse(item);
    }
}
