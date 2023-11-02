package com.growup.growthmate.batch.sentiment.repository;

import com.growup.growthmate.batch.BatchAbstractRepository;
import com.growup.growthmate.company.domain.CompanySentiment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class CompanySentimentBatchRepository extends BatchAbstractRepository<CompanySentiment> {

    private static final String ID = "companySentimentId";
    private static final String COMPANY_ID = "companyId";
    private static final String POSITIVE_RATE = "positiveRate";
    private static final String NEGATIVE_RATE = "negativeRate";

    private static final String INSERT_SQL = "INSERT INTO company_sentiment(" +
            "company_id, positive_rate, negative_rate" +
            ") VALUES(" +
            ":companyId, :positiveRate, :negativeRate" +
            ")";

    private static final String UPDATE_SQL = "UPDATE company_sentiment SET " +
            "positive_rate = :positiveRate, " +
            "negative_rate = :negativeRate " +
            "WHERE company_sentiment_id = :companySentimentId";

    protected CompanySentimentBatchRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, INSERT_SQL, UPDATE_SQL);
    }

    @Override
    protected Map<String, Object> generateEntityParams(CompanySentiment sentiment) {
        Map<String, Object> params = new HashMap<>();
        params.put(COMPANY_ID, sentiment.getCompanyId());
        params.put(POSITIVE_RATE, sentiment.getPositiveRate());
        params.put(NEGATIVE_RATE, sentiment.getNegativeRate());
        Optional.ofNullable(sentiment.getId())
                .ifPresent(id -> params.put(ID, id));
        return params;
    }
}
