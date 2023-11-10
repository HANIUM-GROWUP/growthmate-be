package com.growup.growthmate.batch.comparison.repository;

import com.growup.growthmate.company.domain.CompanyComparison;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompanyComparisonExistsRepository extends Repository<CompanyComparison, Long> {

    @Query("select cc.id from CompanyComparison cc where cc.companyId = :companyId")
    Optional<Long> findIdByCompanyId(@Param("companyId") Long companyId);
}
