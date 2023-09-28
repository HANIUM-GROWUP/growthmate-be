ALTER TABLE company ADD CONSTRAINT comapny_name_unique UNIQUE (name);

ALTER TABLE company MODIFY image_url null;
ALTER TABLE company MODIFY ceo null;
ALTER TABLE company MODIFY scale null;
ALTER TABLE company MODIFY business_type null;
ALTER TABLE company MODIFY business null;
ALTER TABLE company MODIFY establishment_date null;
ALTER TABLE company MODIFY sales null;
ALTER TABLE company MODIFY employee_number null;
ALTER TABLE company MODIFY address null;
