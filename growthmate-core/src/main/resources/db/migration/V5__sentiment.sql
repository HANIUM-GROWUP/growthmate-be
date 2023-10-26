CREATE TABLE company_sentiment
(
    company_sentiment_id BIGINT AUTO_INCREMENT NOT NULL,
    company_id           BIGINT                NOT NULL,
    positive_rate        DOUBLE                NOT NULL,
    negative_rate        DOUBLE                NOT NULL,
    CONSTRAINT pk_companysentiment PRIMARY KEY (company_sentiment_id)
);

ALTER TABLE company_sentiment
    ADD CONSTRAINT FK_COMPANY_SENTIMENT_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (company_id);


CREATE TABLE company_news
(
    company_news_id BIGINT AUTO_INCREMENT NOT NULL,
    company_id      BIGINT                NOT NULL,
    title           VARCHAR(255)          NOT NULL,
    description   LONGTEXT              NOT NULL,
    url             VARCHAR(255)          NOT NULL,
    sentiment       VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_companynews PRIMARY KEY (company_news_id)
);

ALTER TABLE company_news
    ADD CONSTRAINT FK_COMPANY_NEWS_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (company_id);
