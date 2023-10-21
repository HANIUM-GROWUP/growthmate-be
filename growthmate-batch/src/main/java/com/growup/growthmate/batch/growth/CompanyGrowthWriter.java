package com.growup.growthmate.batch.growth;

import com.growup.growthmate.batch.growth.repository.CompanyGrowthBatchRepository;
import com.growup.growthmate.company.domain.CompanyGrowth;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CompanyGrowthWriter implements ItemWriter<List<CompanyGrowth>> {

    private final CompanyGrowthBatchRepository companyGrowthBatchRepository;

    @Override
    public void write(Chunk<? extends List<CompanyGrowth>> chunk) {
        List<CompanyGrowth> insertGrowths = new ArrayList<>();
        List<CompanyGrowth> updateGrowths = new ArrayList<>();

        for (List<CompanyGrowth> growths : chunk) {
            groupGrowths(insertGrowths, updateGrowths, growths);
        }

        companyGrowthBatchRepository.insertAll(insertGrowths);
        companyGrowthBatchRepository.updateAll(updateGrowths);
    }

    private void groupGrowths(List<CompanyGrowth> insertGrowths,
                              List<CompanyGrowth> updateGrowths,
                              List<CompanyGrowth> growths) {
        for (CompanyGrowth growth : growths) {
            groupGrowth(growth, insertGrowths, updateGrowths);
        }
    }

    private void groupGrowth(CompanyGrowth growth,
                             List<CompanyGrowth> insertGrowths,
                             List<CompanyGrowth> updateGrowths) {
        if (growth.getId() == null) {
            insertGrowths.add(growth);
        } else {
            updateGrowths.add(growth);
        }
    }
}
