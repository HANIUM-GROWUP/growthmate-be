package com.growup.growthmate.batch.news;

import com.growup.growthmate.batch.news.repository.CompanyNewsBatchRepository;
import com.growup.growthmate.company.domain.CompanyNews;
import com.growup.growthmate.support.log.ExecutionTimeLog;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CompanyNewsWriter implements ItemWriter<CompanyNews> {

    private final CompanyNewsBatchRepository companyNewsBatchRepository;

    @Override
    @ExecutionTimeLog
    public void write(Chunk<? extends CompanyNews> chunk) {
        List<CompanyNews> insertNews = new ArrayList<>(chunk.getItems());
        companyNewsBatchRepository.insertAll(insertNews);
    }
}
