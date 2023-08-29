INSERT INTO company(company_id) values(1);


INSERT INTO member (member_id, name, email, picture_url, registration_id)
values (1, '이동규', 'ldk@gmail.com', 'picture.com', 'google');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (1, 1, 1, '게시글 제목입니다.', '게시글 내용입니다.', false, '2023-08-28 10:00:00')
