-- 성장 예측
CREATE TABLE growth
(
    growth_id  BIGINT AUTO_INCREMENT NOT NULL,
    company_id BIGINT                NOT NULL,
    year       BIGINT                NOT NULL,
    sales      BIGINT                NOT NULL,
    created_at datetime              NOT NULL,
    CONSTRAINT pk_growth PRIMARY KEY (growth_id)
);

ALTER TABLE growth
    ADD CONSTRAINT FK_GROWTH_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (company_id);

-- 언론
CREATE TABLE news
(
    news_id       BIGINT AUTO_INCREMENT NOT NULL,
    company_id    BIGINT                NOT NULL,
    positive_rate BIGINT                NOT NULL,
    negative_rate BIGINT                NOT NULL,
    created_at    datetime              NOT NULL,
    CONSTRAINT pk_news PRIMARY KEY (news_id)
);

ALTER TABLE news
    ADD CONSTRAINT FK_NEWS_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (company_id);

-- 평균
CREATE TABLE average
(
    average_id  BIGINT AUTO_INCREMENT NOT NULL,
    industry_id BIGINT                NOT NULL,
    company_id  BIGINT                NOT NULL,
    category    LONGTEXT               NOT NULL,
    score       BIGINT                NOT NULL,
    created_at  datetime              NOT NULL,
    CONSTRAINT pk_average PRIMARY KEY (average_id)
);

ALTER TABLE average
    ADD CONSTRAINT FK_AVERAGE_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (company_id);

-- 업종
CREATE TABLE industry
(
    industry_id  BIGINT AUTO_INCREMENT NOT NULL,
    average      BIGINT                NOT NULL,
    high_average BIGINT                NOT NULL,
    created_at   datetime              NOT NULL,
    CONSTRAINT pk_industry PRIMARY KEY (industry_id)
);