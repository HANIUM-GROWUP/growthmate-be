package com.growup.growthmate.fixture;

import com.growup.growthmate.company.domain.CompanyGrowth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyGrowthFixtureRepository extends JpaRepository<CompanyGrowth, Long> {
}
