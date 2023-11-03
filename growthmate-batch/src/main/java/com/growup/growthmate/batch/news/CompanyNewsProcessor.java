package com.growup.growthmate.batch.news;

import com.growup.growthmate.batch.company.repository.CompanyExistsRepository;
import com.growup.growthmate.company.domain.CompanyNews;
import com.growup.growthmate.company.domain.Sentiment;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyNewsProcessor implements ItemProcessor<CompanyNewsDto, CompanyNews> {

    private final CompanyExistsRepository companyExistsRepository;

    @Override
    public CompanyNews process(CompanyNewsDto item) {
        return companyExistsRepository.findIdByName(item.name())
                .map(companyId -> new CompanyNews(
                        companyId,
                        item.title(),
                        item.description(),
                        item.url(),
                        Sentiment.valueOf(item.sentiment().toUpperCase())
                ))
                .orElse(null);
    }
}
