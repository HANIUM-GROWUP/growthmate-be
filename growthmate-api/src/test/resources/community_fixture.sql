INSERT INTO company(company_id) values(1);

INSERT INTO member (member_id, name, email, picture_url, registration_id)
values (1, '이동규', 'ldk@gmail.com', 'picture.com', 'google');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (1, 1, 1, '게시글 제목입니다.', '게시글 내용입니다.', false, '2023-08-28 10:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (2, 1, 1, '삭제된 게시글 제목입니다.', '삭제된 게시글 내용입니다.', true, '2023-08-29 10:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (3, 1, 1, '게시글 제목입니다.', '게시글 내용입니다.', false, '2023-08-29 10:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (4, 1, 1, '게시글 제목입니다.', '게시글 내용입니다.', false, '2023-08-29 11:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (5, 1, 1, '게시글 제목입니다.', '게시글 내용입니다.', false, '2023-08-29 12:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (6, 1, 1, '게시글 제목입니다.', '게시글 내용입니다.', false, '2023-08-29 13:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (7, 1, 1, '게시글 제목입니다.', '게시글 내용입니다.', false, '2023-08-29 14:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (8, 1, 1, '게시글 제목입니다.', '게시글 내용입니다.', false, '2023-08-29 15:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (9, 1, 1, '게시글 제목입니다.', '게시글 내용입니다.', false, '2023-08-29 16:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (10, 1, 1, '게시글 제목입니다.', '게시글 내용입니다.', false, '2023-08-29 17:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (11, 1, 1, '게시글 제목입니다.', '게시글 내용입니다.', false, '2023-08-29 18:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (12, 1, 1, '게시글 제목입니다.', '게시글 내용입니다.', false, '2023-08-29 19:00:00');


INSERT INTO company(company_id) values(2);

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (13, 1, 2, '게시글 제목입니다.', '게시글 내용입니다.', false, '2023-08-29 19:00:00');

