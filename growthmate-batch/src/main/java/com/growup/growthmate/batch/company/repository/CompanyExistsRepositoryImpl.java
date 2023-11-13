package com.growup.growthmate.batch.company.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CompanyExistsRepositoryImpl implements CompanyExistsRepository {

    private final CompanyExistsJpaRepository companyExistsJpaRepository;
    private final Map<String, Optional<Long>> cache = new HashMap<>();

    @Override
    public Optional<Long> findIdByName(String name) {
        if (cache.containsKey(name)) {
            return cache.get(name);
        }
        Optional<Long> companyId = companyExistsJpaRepository.findIdByName(name);
        cache.put(name, companyId);
        return companyId;
    }
}
