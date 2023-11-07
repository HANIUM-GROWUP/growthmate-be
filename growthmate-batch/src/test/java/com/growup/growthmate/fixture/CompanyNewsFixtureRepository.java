package com.growup.growthmate.fixture;

import com.growup.growthmate.company.domain.CompanyNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyNewsFixtureRepository extends JpaRepository<CompanyNews, Long> {
}
