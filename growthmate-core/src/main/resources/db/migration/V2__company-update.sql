ALTER TABLE company ADD COLUMN name varchar(255) NOT NULL;


CREATE TABLE company_analysis
(
    company_analysis_id BIGINT AUTO_INCREMENT NOT NULL,
    company_id          BIGINT                NOT NULL,
    growth              DOUBLE                NOT NULL,
    efficiency          DOUBLE                NOT NULL,
    profitability       DOUBLE                NOT NULL,
    technology          DOUBLE                NOT NULL,
    financial_stability DOUBLE                NOT NULL,
    CONSTRAINT pk_companyanalysis PRIMARY KEY (company_analysis_id)
);

ALTER TABLE company_analysis
    ADD CONSTRAINT FK_COMPANYANALYSIS_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (company_id);


