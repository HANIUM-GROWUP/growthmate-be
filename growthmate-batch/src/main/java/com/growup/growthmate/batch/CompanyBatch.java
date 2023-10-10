package com.growup.growthmate.batch;

import com.growup.growthmate.batch.analysis.CompanyAnalysisDto;
import com.growup.growthmate.batch.analysis.CompanyAnalysisProcessor;
import com.growup.growthmate.batch.analysis.CompanyAnalysisReader;
import com.growup.growthmate.batch.analysis.CompanyAnalysisWriter;
import com.growup.growthmate.batch.company.CompanyProcessor;
import com.growup.growthmate.batch.company.CompanyReader;
import com.growup.growthmate.batch.company.CompanyWriter;
import com.growup.growthmate.company.domain.Company;
import com.growup.growthmate.company.domain.CompanyAnalysis;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

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

    @Value("${batch.company.chunk-size:100}")
    private int chunkSize;

    @Bean
    public Job updateCompanyInfoJob() {
        return new JobBuilder("companyJob", jobRepository)
                .start(updateCompanyInfoStep())
                .next(updateCompanyAnalysisStep())
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
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public JobExecutionListener jobExecutionTimeListener() {
        return new JobExecutionTimeListener();
    }
}
