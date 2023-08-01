CREATE TABLE member
(
    member_id BIGINT AUTO_INCREMENT NOT NULL,
    CONSTRAINT pk_member PRIMARY KEY (member_id)
);

CREATE TABLE company
(
    company_id BIGINT AUTO_INCREMENT NOT NULL,
    CONSTRAINT pk_company PRIMARY KEY (company_id)
);
