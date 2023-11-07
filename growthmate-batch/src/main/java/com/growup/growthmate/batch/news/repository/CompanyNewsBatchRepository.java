package com.growup.growthmate.batch.news.repository;

import com.growup.growthmate.batch.BatchAbstractRepository;
import com.growup.growthmate.company.domain.CompanyNews;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CompanyNewsBatchRepository extends BatchAbstractRepository<CompanyNews> {

    private static final String COMPANY_ID = "companyId";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String URL = "url";
    private static final String SENTIMENT = "sentiment";

    private static final String INSERT_SQL = "INSERT INTO company_news(" +
            "company_id, title, description, url, sentiment" +
            ") VALUES(" +
            ":companyId, :title, :description, :url, :sentiment" +
            ")";


    protected CompanyNewsBatchRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, INSERT_SQL, null);
    }

    @Override
    protected Map<String, Object> generateEntityParams(CompanyNews news) {
        Map<String, Object> params = new HashMap<>();
        params.put(COMPANY_ID, news.getCompanyId());
        params.put(TITLE, news.getTitle());
        params.put(DESCRIPTION, news.getDescription());
        params.put(URL, news.getUrl());
        params.put(SENTIMENT, news.getSentiment().name());
        return params;
    }
}
