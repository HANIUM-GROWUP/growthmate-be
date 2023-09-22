package com.growup.growthmate.fixture;

import com.growup.growthmate.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyFixtureRepository extends JpaRepository<Company, Long> {
}
