CREATE TABLE company_comparison
(
    company_comparision_id    BIGINT AUTO_INCREMENT NOT NULL,
    company_id                BIGINT                NOT NULL,
    sales_forecast            BIGINT                NOT NULL,
    sales_forecast_percentage INT                NOT NULL,
    CONSTRAINT pk_companycomparison PRIMARY KEY (company_comparision_id)
);

ALTER TABLE company_comparison
    ADD CONSTRAINT FK_COMPANY_COMPARISON_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (company_id);
