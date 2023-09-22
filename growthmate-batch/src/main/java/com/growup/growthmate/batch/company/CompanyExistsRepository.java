package com.growup.growthmate.batch.company;

import com.growup.growthmate.company.domain.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompanyExistsRepository extends Repository<Company, Long> {

    @Query("select c.id from Company c where c.name = :name")
    Optional<Long> findIdByName(@Param("name") String name);
}
