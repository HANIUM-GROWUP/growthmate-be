package com.growup.growthmate.fixture;

import com.growup.growthmate.company.domain.CompanyComparison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyComparisonFixtureRepository extends JpaRepository<CompanyComparison, Long> {
}
