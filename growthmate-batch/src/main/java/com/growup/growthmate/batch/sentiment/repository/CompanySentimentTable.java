package com.growup.growthmate.batch.sentiment.repository;

public class CompanySentimentTable {

    public static final String ID = "companySentimentId";
    public static final String COMPANY_ID = "companyId";
    public static final String POSITIVE_RATE = "positiveRate";
    public static final String NEGATIVE_RATE = "negativeRate";

    public static final String INSERT_SQL = "INSERT INTO company_sentiment(" +
            "company_id, positive_rate, negative_rate" +
            ") VALUES(" +
            ":companyId, :positiveRate, :negativeRate" +
            ")";

    public static final String UPDATE_SQL = "UPDATE company_sentiment SET " +
            "positive_rate = :positiveRate, " +
            "negative_rate = :negativeRate " +
            "WHERE company_sentiment_id = :companySentimentId";
}
