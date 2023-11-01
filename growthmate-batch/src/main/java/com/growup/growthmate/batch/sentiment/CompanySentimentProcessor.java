package com.growup.growthmate.batch.sentiment;

import com.growup.growthmate.batch.company.repository.CompanyExistsRepository;
import com.growup.growthmate.batch.sentiment.repository.CompanySentimentExistsRepository;
import com.growup.growthmate.company.domain.CompanySentiment;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanySentimentProcessor implements ItemProcessor<CompanySentimentDto, CompanySentiment> {

    private final CompanySentimentExistsRepository companySentimentExistsRepository;
    private final CompanyExistsRepository companyExistsRepository;

    @Override
    public CompanySentiment process(CompanySentimentDto item) {
        return companyExistsRepository.findIdByName(item.name())
                .map(companyId -> mapToCompanySentiment(item, companyId))
                .orElse(null);
    }

    private CompanySentiment mapToCompanySentiment(CompanySentimentDto item, Long companyId) {
        Double positiveRate = item.positiveRate();
        Double negativeRate = item.negativeRate();
        return companySentimentExistsRepository.findIdByCompanyId(companyId)
                .map(sentimentId -> new CompanySentiment(sentimentId, companyId, positiveRate, negativeRate))
                .orElse(new CompanySentiment(companyId, positiveRate, negativeRate));
    }
}
