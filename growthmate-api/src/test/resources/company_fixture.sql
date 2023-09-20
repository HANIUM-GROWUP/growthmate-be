-- member 테이블 sample data
INSERT INTO member (member_id, name, email, picture_url, registration_id)
values (1, '안정후', 'ajh@gmail.com', 'picture.com', 'google');

-- company 테이블 sample data
INSERT INTO company(company_id, name, image_url, ceo, scale, business_type, business, establishment_date, sales, employee_number, address)
values (1, '비트 망고', 'picure.com', '이기섭', '중견기업', '모바일 게임, 소프트웨어 공급업', '게임 소프트웨어', '1996-07-01 00:00:00', 139485530000, 43, '경기도 성남시 분당구 대왕판교로645번길 14 네오위즈판교타워 3층 (우)13487');

-- company analysis 테이블 sample data
INSERT INTO company_analysis (company_analysis_id, company_id, growth, stability, profitability, efficiency,
                              business_performance)
values (1, 1, 80, 70, 90, 50, 60);
