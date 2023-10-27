package com.growup.growthmate.batch.sentiment;

import com.growup.growthmate.batch.sentiment.repository.CompanySentimentBatchRepository;
import com.growup.growthmate.company.domain.CompanySentiment;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CompanySentimentWriter implements ItemWriter<CompanySentiment> {

    private final CompanySentimentBatchRepository companySentimentBatchRepository;

    @Override
    public void write(Chunk<? extends CompanySentiment> chunk) {
        List<CompanySentiment> insertSentiments = new ArrayList<>();
        List<CompanySentiment> updateSentiments = new ArrayList<>();

        for (CompanySentiment sentiment : chunk) {
            groupSentiments(sentiment, insertSentiments, updateSentiments);
        }

        companySentimentBatchRepository.insertAll(insertSentiments);
        companySentimentBatchRepository.updateAll(updateSentiments);
    }

    private void groupSentiments(CompanySentiment sentiment,
                                 List<CompanySentiment> insertSentiments,
                                 List<CompanySentiment> updateSentiments) {
        if (sentiment.getId() == null) {
            insertSentiments.add(sentiment);
        } else {
            updateSentiments.add(sentiment);
        }
    }
}
