package com.growup.growthmate.batch;

import com.growup.growthmate.batch.analysis.CompanyAnalysisDto;
import com.growup.growthmate.batch.analysis.CompanyAnalysisProcessor;
import com.growup.growthmate.batch.analysis.CompanyAnalysisReader;
import com.growup.growthmate.batch.analysis.CompanyAnalysisWriter;
import com.growup.growthmate.batch.company.CompanyProcessor;
import com.growup.growthmate.batch.company.CompanyReader;
import com.growup.growthmate.batch.company.CompanyWriter;
import com.growup.growthmate.batch.comparison.CompanyComparisonDto;
import com.growup.growthmate.batch.comparison.CompanyComparisonProcessor;
import com.growup.growthmate.batch.comparison.CompanyComparisonReader;
import com.growup.growthmate.batch.comparison.CompanyComparisonWriter;
import com.growup.growthmate.batch.growth.CompanyGrowthDto;
import com.growup.growthmate.batch.growth.CompanyGrowthProcessor;
import com.growup.growthmate.batch.growth.CompanyGrowthReader;
import com.growup.growthmate.batch.growth.CompanyGrowthWriter;
import com.growup.growthmate.batch.news.CompanyNewsDto;
import com.growup.growthmate.batch.news.CompanyNewsProcessor;
import com.growup.growthmate.batch.news.CompanyNewsReader;
import com.growup.growthmate.batch.news.CompanyNewsWriter;
import com.growup.growthmate.batch.sentiment.CompanySentimentDto;
import com.growup.growthmate.batch.sentiment.CompanySentimentProcessor;
import com.growup.growthmate.batch.sentiment.CompanySentimentReader;
import com.growup.growthmate.batch.sentiment.CompanySentimentWriter;
import com.growup.growthmate.company.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CompanyBatch {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final CompanyReader companyReader;
    private final CompanyProcessor companyProcessor;
    private final CompanyWriter companyWriter;

    private final CompanyAnalysisReader companyAnalysisReader;
    private final CompanyAnalysisProcessor companyAnalysisProcessor;
    private final CompanyAnalysisWriter companyAnalysisWriter;

    private final CompanyGrowthReader companyGrowthReader;
    private final CompanyGrowthProcessor companyGrowthProcessor;
    private final CompanyGrowthWriter companyGrowthWriter;

    private final CompanySentimentReader companySentimentReader;
    private final CompanySentimentProcessor companySentimentProcessor;
    private final CompanySentimentWriter companySentimentWriter;

    private final CompanyNewsReader companyNewsReader;
    private final CompanyNewsProcessor companyNewsProcessor;
    private final CompanyNewsWriter companyNewsWriter;

    private final CompanyComparisonReader companyComparisonReader;
    private final CompanyComparisonProcessor companyComparisonProcessor;
    private final CompanyComparisonWriter companyComparisonWriter;

    @Value("${batch.company.chunk-size:100}")
    private int chunkSize;

    @Bean
    public Job updateCompanyJob() {
        return new JobBuilder("companyJob", jobRepository)
                .start(updateCompanyInfoStep())
                .next(updateCompanyAnalysisStep())
                .next(updateCompanyGrowthStep())
                .next(updateCompanySentimentStep())
                .next(insertCompanyNewsStep())
                .next(updateCompanyComparisonStep())
                .listener(jobExecutionTimeListener())
                .build();
    }

    @Bean
    public Step updateCompanyInfoStep() {
        return new StepBuilder("companyInfoUpdate", jobRepository)
                .<Company, Company>chunk(chunkSize, transactionManager)
                .reader(companyReader)
                .processor(companyProcessor)
                .writer(companyWriter)
                .listener(stepExecutionTimeListener())
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step updateCompanyAnalysisStep() {
        return new StepBuilder("companyAnalysisUpdate", jobRepository)
                .<CompanyAnalysisDto, CompanyAnalysis>chunk(chunkSize, transactionManager)
                .reader(companyAnalysisReader)
                .processor(companyAnalysisProcessor)
                .writer(companyAnalysisWriter)
                .listener(stepExecutionTimeListener())
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step updateCompanyGrowthStep() {
        return new StepBuilder("updateCompanyGrowthStep", jobRepository)
                .<List<CompanyGrowthDto>, List<CompanyGrowth>>chunk(chunkSize, transactionManager)
                .reader(companyGrowthReader)
                .processor(companyGrowthProcessor)
                .writer(companyGrowthWriter)
                .listener(stepExecutionTimeListener())
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step updateCompanySentimentStep() {
        return new StepBuilder("updateCompanySentimentStep", jobRepository)
                .<CompanySentimentDto, CompanySentiment>chunk(chunkSize, transactionManager)
                .reader(companySentimentReader)
                .processor(companySentimentProcessor)
                .writer(companySentimentWriter)
                .listener(stepExecutionTimeListener())
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step insertCompanyNewsStep() {
        return new StepBuilder("insertCompanyNewsStep", jobRepository)
                .<CompanyNewsDto, CompanyNews>chunk(chunkSize, transactionManager)
                .reader(companyNewsReader)
                .processor(companyNewsProcessor)
                .writer(companyNewsWriter)
                .listener(stepExecutionTimeListener())
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step updateCompanyComparisonStep() {
        return new StepBuilder("updateCompanyComparisonStep", jobRepository)
                .<CompanyComparisonDto, CompanyComparison>chunk(chunkSize, transactionManager)
                .reader(companyComparisonReader)
                .processor(companyComparisonProcessor)
                .writer(companyComparisonWriter)
                .listener(stepExecutionTimeListener())
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public JobExecutionListener jobExecutionTimeListener() {
        return new JobExecutionTimeListener();
    }

    @Bean
    public StepExecutionListener stepExecutionTimeListener() {
        return new StepExecutionTimeListener();
    }
}
