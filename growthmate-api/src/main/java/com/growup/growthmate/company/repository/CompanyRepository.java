package com.growup.growthmate.company.repository;

import com.growup.growthmate.company.domain.CompanyAnalysis;
import com.growup.growthmate.company.repository.analysis.CompanyAnalysisRepository;
import com.growup.growthmate.company.repository.find.CompanyFindRepository;
import com.growup.growthmate.company.repository.growth.CompanyGrowthRepository;
import com.growup.growthmate.company.repository.news.CompanyNewsRepository;
import com.growup.growthmate.company.repository.sentiment.CompanySentimentRepository;
import org.springframework.data.repository.Repository;

public interface CompanyRepository extends Repository<CompanyAnalysis, Long>,
        CompanyAnalysisRepository,
        CompanyFindRepository,
        CompanyGrowthRepository,
        CompanySentimentRepository,
        CompanyNewsRepository {

}
