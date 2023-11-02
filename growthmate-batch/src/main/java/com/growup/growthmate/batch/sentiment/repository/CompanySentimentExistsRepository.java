package com.growup.growthmate.batch.sentiment.repository;

import com.growup.growthmate.company.domain.CompanySentiment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompanySentimentExistsRepository extends Repository<CompanySentiment, Long> {

    @Query("select cs.id from CompanySentiment cs where cs.companyId = :companyId")
    Optional<Long> findIdByCompanyId(@Param("companyId") Long companyId);
}
