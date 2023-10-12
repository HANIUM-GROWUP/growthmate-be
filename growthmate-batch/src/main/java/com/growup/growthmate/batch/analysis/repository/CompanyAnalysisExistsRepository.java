package com.growup.growthmate.batch.analysis.repository;

import com.growup.growthmate.company.domain.CompanyAnalysis;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompanyAnalysisExistsRepository extends Repository<CompanyAnalysis, Long> {

    @Query("select ca.id from CompanyAnalysis ca where ca.companyId = :companyId")
    Optional<Long> findIdByCompanyId(@Param("companyId") Long companyId);
}
