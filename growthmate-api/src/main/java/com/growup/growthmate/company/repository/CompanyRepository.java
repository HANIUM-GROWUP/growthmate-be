package com.growup.growthmate.company.repository;

import com.growup.growthmate.company.domain.CompanyAnalysis;
import org.springframework.data.repository.Repository;

public interface CompanyRepository extends Repository<CompanyAnalysis, Long>, CompanyAnalysisRepository {

}
