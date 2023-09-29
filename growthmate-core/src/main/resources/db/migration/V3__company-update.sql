ALTER TABLE company ADD CONSTRAINT comapny_name_unique UNIQUE (name);

ALTER TABLE company MODIFY COLUMN image_url LONGTEXT null;
ALTER TABLE company MODIFY COLUMN ceo VARCHAR(255) null;
ALTER TABLE company MODIFY COLUMN scale VARCHAR(255) null;
ALTER TABLE company MODIFY COLUMN business_type VARCHAR(255) null;
ALTER TABLE company MODIFY COLUMN business VARCHAR(255) null;
ALTER TABLE company MODIFY COLUMN establishment_date datetime null;
ALTER TABLE company MODIFY COLUMN sales BIGINT null;
ALTER TABLE company MODIFY COLUMN employee_number BIGINT null;
ALTER TABLE company MODIFY COLUMN address VARCHAR(255) null;
