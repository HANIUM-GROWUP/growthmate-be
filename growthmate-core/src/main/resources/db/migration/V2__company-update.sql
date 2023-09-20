ALTER TABLE company ADD COLUMN name varchar(255) NOT NULL;

ALTER TABLE company ADD COLUMN image_url VARCHAR(255) NOT NULL;
ALTER TABLE company ADD COLUMN ceo VARCHAR(255) NOT NULL;
ALTER TABLE company ADD COLUMN scale VARCHAR(255) NOT NULL;
ALTER TABLE company ADD COLUMN business_type VARCHAR(255) NOT NULL;
ALTER TABLE company ADD COLUMN business VARCHAR(255) NOT NULL;
ALTER TABLE company ADD COLUMN establishment_date datetime NOT NULL;
ALTER TABLE company ADD COLUMN sales  BIGINT NOT NULL;
ALTER TABLE company ADD COLUMN employee_number BIGINT NOT NULL;
ALTER TABLE company ADD COLUMN address VARCHAR(255) NOT NULL;


CREATE TABLE company_analysis
(
    company_analysis_id BIGINT AUTO_INCREMENT NOT NULL,
    company_id          BIGINT                NOT NULL,
    growth               INT                   NOT NULL,
    stability            INT                   NOT NULL,
    profitability        INT                   NOT NULL,
    efficiency           INT                   NOT NULL,
    business_performance INT                   NOT NULL,
    CONSTRAINT pk_companyanalysis PRIMARY KEY (company_analysis_id)
);

ALTER TABLE company_analysis
    ADD CONSTRAINT FK_COMPANYANALYSIS_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (company_id);


