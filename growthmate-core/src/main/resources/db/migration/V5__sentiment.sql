CREATE TABLE company_sentiment
(
    company_sentiment_id BIGINT AUTO_INCREMENT NOT NULL,
    company_id           BIGINT                NOT NULL,
    positive_rate        DOUBLE                NULL,
    negative_rate        DOUBLE                NULL,
    CONSTRAINT pk_companysentiment PRIMARY KEY (company_sentiment_id)
);

ALTER TABLE company_sentiment
    ADD CONSTRAINT FK_COMPANY_SENTIMENT_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (company_id);
