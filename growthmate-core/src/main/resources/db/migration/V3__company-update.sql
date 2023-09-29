ALTER TABLE company ADD CONSTRAINT comapny_name_unique UNIQUE (name);

ALTER TABLE company MODIFY COLUMN image_url LONGTEXT null;
ALTER TABLE company MODIFY COLUMN ceo null;
ALTER TABLE company MODIFY COLUMN scale null;
ALTER TABLE company MODIFY COLUMN business_type null;
ALTER TABLE company MODIFY COLUMN business null;
ALTER TABLE company MODIFY COLUMN establishment_date null;
ALTER TABLE company MODIFY COLUMN sales null;
ALTER TABLE company MODIFY COLUMN employee_number null;
ALTER TABLE company MODIFY COLUMN address null;
