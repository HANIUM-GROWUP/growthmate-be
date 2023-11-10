package com.growup.growthmate.batch.comparison;

import com.growup.growthmate.batch.comparison.repository.CompanyComparisonBatchRepository;
import com.growup.growthmate.company.domain.CompanyComparison;
import com.growup.growthmate.support.log.ExecutionTimeLog;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CompanyComparisonWriter implements ItemWriter<CompanyComparison> {

    private final CompanyComparisonBatchRepository companyComparisonBatchRepository;

    @Override
    @ExecutionTimeLog
    public void write(Chunk<? extends CompanyComparison> chunk) {
        List<CompanyComparison> insertComparisons = new ArrayList<>();
        List<CompanyComparison> updateComparisons = new ArrayList<>();

        for (CompanyComparison comparison : chunk) {
            groupComparison(comparison, insertComparisons, updateComparisons);
        }

        companyComparisonBatchRepository.insertAll(insertComparisons);
        companyComparisonBatchRepository.updateAll(updateComparisons);
    }

    private void groupComparison(CompanyComparison comparison,
                                 List<CompanyComparison> insertComparisons,
                                 List<CompanyComparison> updateComparisons) {
        if (comparison.getId() == null) {
            insertComparisons.add(comparison);
        } else {
            updateComparisons.add(comparison);
        }
    }
}
