package com.growup.growthmate.batch.company.repository;

import java.util.Optional;

public interface CompanyExistsRepository {

    Optional<Long> findIdByName(String name);
}
