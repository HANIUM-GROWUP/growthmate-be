package com.growup.growthmate.batch.company;

import com.growup.growthmate.batch.company.repository.CompanyBatchRepository;
import com.growup.growthmate.company.domain.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CompanyWriter implements ItemWriter<Company> {

    private final CompanyBatchRepository companyBatchRepository;

    @Override
    public void write(Chunk<? extends Company> chunk) {
        List<Company> insertCompanies = new ArrayList<>();
        List<Company> updateCompanies = new ArrayList<>();

        for (Company company : chunk) {
            groupCompanies(company, insertCompanies, updateCompanies);
        }
        
        companyBatchRepository.insertAll(insertCompanies);
        companyBatchRepository.updateAll(updateCompanies);
    }

    private void groupCompanies(Company company, List<Company> insertCompanies, List<Company> updateCompanies) {
        if (company.getId() == null) {
            insertCompanies.add(company);
        } else {
            updateCompanies.add(company);
        }
    }
}
