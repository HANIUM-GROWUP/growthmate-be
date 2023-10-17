-- 성장 예측
CREATE TABLE company_growth
(
    company_growth_id BIGINT AUTO_INCREMENT  NOT NULL,
    company_id        BIGINT                 NOT NULL,
    years             INT                    NOT NULL,
    sales             DOUBLE                 NOT NULL,
    CONSTRAINT pk_growth PRIMARY KEY (company_growth_id)
);

ALTER TABLE company_growth
    ADD CONSTRAINT FK_COMPANY_GROWTH_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (company_id);