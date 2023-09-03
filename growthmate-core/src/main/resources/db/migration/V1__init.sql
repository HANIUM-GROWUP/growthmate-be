CREATE TABLE member
(
    member_id BIGINT AUTO_INCREMENT NOT NULL,
    name            VARCHAR(255)    NOT NULL,
    email           VARCHAR(255)    NOT NULL,
    picture_url     VARCHAR(255)    NOT NULL,
    registration_id VARCHAR(255)    NOT NULL,
    CONSTRAINT pk_member PRIMARY KEY (member_id)
);

CREATE TABLE company
(
    company_id BIGINT AUTO_INCREMENT NOT NULL,
    CONSTRAINT pk_company PRIMARY KEY (company_id)
);

CREATE TABLE post
(
    post_id    BIGINT AUTO_INCREMENT NOT NULL,
    member_id  BIGINT                NOT NULL,
    company_id BIGINT                NOT NULL,
    title      VARCHAR(255)          NOT NULL,
    content    LONGTEXT              NOT NULL,
    is_deleted BIT                   NOT NULL,
    created_at  datetime              NOT NULL,
    CONSTRAINT pk_post PRIMARY KEY (post_id)
);

ALTER TABLE post
    ADD CONSTRAINT FK_POST_ON_COMPANY FOREIGN KEY (company_id) REFERENCES company (company_id);

ALTER TABLE post
    ADD CONSTRAINT FK_POST_ON_MEMBER FOREIGN KEY (member_id) REFERENCES member (member_id);

CREATE TABLE comment
(
    comment_id BIGINT AUTO_INCREMENT NOT NULL,
    post_id    BIGINT                NOT NULL,
    member_id  BIGINT                NOT NULL,
    content    LONGTEXT              NOT NULL,
    is_deleted BIT                   NOT NULL,
    created_at  datetime              NOT NULL,
    CONSTRAINT pk_comment PRIMARY KEY (comment_id)
);

ALTER TABLE comment
    ADD CONSTRAINT FK_COMMENT_ON_MEMBER FOREIGN KEY (member_id) REFERENCES member (member_id);

ALTER TABLE comment
    ADD CONSTRAINT FK_COMMENT_ON_POST FOREIGN KEY (post_id) REFERENCES post (post_id);
