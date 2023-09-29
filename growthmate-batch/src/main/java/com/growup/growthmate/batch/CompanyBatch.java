package com.growup.growthmate.batch;

import com.growup.growthmate.batch.company.CompanyProcessor;
import com.growup.growthmate.batch.company.CompanyReader;
import com.growup.growthmate.batch.company.CompanyWriter;
import com.growup.growthmate.company.domain.Company;
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

    @Value("${batch.company.chunk-size:100}")
    private int chunkSize;

    @Bean
    public Job updateCompanyInfoJob() {
        return new JobBuilder("companyJob", jobRepository)
                .start(updateCompanyInfoStep())
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
                .build();
    }

    @Bean
    public JobExecutionListener jobExecutionTimeListener() {
        return new JobExecutionTimeListener();
    }
}
