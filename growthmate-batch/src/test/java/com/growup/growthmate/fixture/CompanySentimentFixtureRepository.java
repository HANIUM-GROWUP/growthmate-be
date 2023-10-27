package com.growup.growthmate.fixture;

import com.growup.growthmate.company.domain.CompanySentiment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanySentimentFixtureRepository extends JpaRepository<CompanySentiment, Long> {
}
