package com.growup.growthmate;

import com.growup.growthmate.company.CompanyReader;
import com.growup.growthmate.company.CompanyWriter;
import com.growup.growthmate.company.domain.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class CompanyBatchJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final CompanyReader companyReader;
    private final CompanyWriter companyWriter;

    @Bean
    public Job updateCompanyInfoJob() {
        return new JobBuilder("companyJob", jobRepository)
                .start(updateCompanyInfoStep())
                .build();
    }

    @Bean
    public Step updateCompanyInfoStep() {
        return new StepBuilder("companyInfoUpdate", jobRepository)
                .<Company, Company>chunk(10, transactionManager)
                .reader(companyReader)
                .writer(companyWriter)
                .build();
    }
}
