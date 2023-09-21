package com.growup.growthmate.company;

import com.growup.growthmate.company.domain.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyWriter implements ItemWriter<Company> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void write(Chunk<? extends Company> chunk) throws Exception {
        // todo
    }
}
