package com.growup.growthmate.batch.analysis;

import com.growup.growthmate.batch.analysis.repository.CompanyAnalysisBatchRepository;
import com.growup.growthmate.company.domain.CompanyAnalysis;
import com.growup.growthmate.support.log.ExecutionTimeLog;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CompanyAnalysisWriter implements ItemWriter<CompanyAnalysis> {

    private final CompanyAnalysisBatchRepository companyAnalysisBatchRepository;

    @Override
    @ExecutionTimeLog
    public void write(Chunk<? extends CompanyAnalysis> chunk) {
        List<CompanyAnalysis> insertAnalyses = new ArrayList<>();
        List<CompanyAnalysis> updateAnalyses = new ArrayList<>();

        for (CompanyAnalysis analysis : chunk) {
            groupAnalyses(analysis, insertAnalyses, updateAnalyses);
        }

        companyAnalysisBatchRepository.insertAll(insertAnalyses);
        companyAnalysisBatchRepository.updateAll(updateAnalyses);
    }

    private void groupAnalyses(CompanyAnalysis analysis,
                               List<CompanyAnalysis> insertCompanies,
                               List<CompanyAnalysis> updateCompanies) {
        if (analysis.getId() == null) {
            insertCompanies.add(analysis);
        } else {
            updateCompanies.add(analysis);
        }
    }
}
