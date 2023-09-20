-- member 테이블 sample data
INSERT INTO member (member_id, name, email, picture_url, registration_id)
values (1, '안정후', 'ajh@gmail.com', 'picture.com', 'google');

-- company 테이블 sample data
INSERT INTO company(company_id, name)
values (1, 'Naver');

-- company analysis 테이블 sample data
INSERT INTO company_analysis (company_analysis_id, company_id, growth, stability, profitability, efficiency,
                              business_performance)
values (1, 1, 80, 70, 90, 50, 60);
