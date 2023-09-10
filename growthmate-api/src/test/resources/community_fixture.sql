-- 회원
INSERT INTO member (member_id, name, email, picture_url, registration_id)
values (1, '이동규', 'ldk@gmail.com', 'picture.com', 'google');

-- 1번 회사
INSERT INTO company(company_id, name) values (1, 'Naver');

-- 1번 회사 게시글 14개 (삭제 게시글 1개)
INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (1, 1, 1, '게시글 제목입니다.', '게시글 내용입니다. 10글자를 넘어갑니다.', false, '2023-08-28 10:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (2, 1, 1, '삭제된 게시글 제목입니다.', '삭제된 게시글 내용입니다.', true, '2023-08-29 10:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (3, 1, 1, '게시글 제목입니다.', '게시글 내용입니다. 10글자를 넘어갑니다..', false, '2023-08-29 10:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (4, 1, 1, '게시글 제목입니다.', '게시글 내용입니다. 10글자를 넘어갑니다..', false, '2023-08-29 11:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (5, 1, 1, '게시글 제목입니다.', '게시글 내용입니다. 10글자를 넘어갑니다.', false, '2023-08-29 12:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (6, 1, 1, '게시글 제목입니다.', '게시글 내용입니다. 10글자를 넘어갑니다.', false, '2023-08-29 13:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (7, 1, 1, '게시글 제목입니다.', '게시글 내용입니다. 10글자를 넘어갑니다.', false, '2023-08-29 14:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (8, 1, 1, '게시글 제목입니다.', '게시글 내용입니다. 10글자를 넘어갑니다.', false, '2023-08-29 15:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (9, 1, 1, '게시글 제목입니다.', '게시글 내용입니다. 10글자를 넘어갑니다.', false, '2023-08-29 16:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (10, 1, 1, '게시글 제목입니다.', '게시글 내용입니다. 10글자를 넘어갑니다.', false, '2023-08-29 17:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (11, 1, 1, '게시글 제목입니다.', '게시글 내용입니다. 10글자를 넘어갑니다.', false, '2023-08-29 18:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (12, 1, 1, '게시글 제목입니다.', '게시글 내용입니다. 10글자를 넘어갑니다.', false, '2023-08-29 19:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (13, 1, 1, '게시글 제목입니다.', '게시글 내용입니다. 10글자를 넘어갑니다.', false, '2023-08-29 19:00:00');

INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (14, 1, 1, '게시글 제목입니다.', '게시글 내용입니다. 10글자를 넘어갑니다.', false, '2023-08-29 19:00:00');


-- 14번 게시글 댓글 3개
INSERT INTO comment(comment_id, post_id, member_id, content, is_deleted, created_at)
values (1, 14, 1, '댓글 내용입니다.', false, '2023-08-29 20:00:00');

INSERT INTO comment(comment_id, post_id, member_id, content, is_deleted, created_at)
values (2, 14, 1, '댓글 내용입니다.', false, '2023-08-29 20:00:30');

INSERT INTO comment(comment_id, post_id, member_id, content, is_deleted, created_at)
values (3, 14, 1, '댓글 내용입니다.', false, '2023-08-29 20:00:45');

-- 13번 게시글 댓글 2개
INSERT INTO comment(comment_id, post_id, member_id, content, is_deleted, created_at)
values (4, 13, 1, '댓글 내용입니다.', false, '2023-08-29 20:00:45');

INSERT INTO comment(comment_id, post_id, member_id, content, is_deleted, created_at)
values (5, 13, 1, '댓글 내용입니다.', false, '2023-08-29 20:00:45');

-- 12번 게시글 댓글 1개
INSERT INTO comment(comment_id, post_id, member_id, content, is_deleted, created_at)
values (6, 12, 1, '댓글 내용입니다.', false, '2023-08-29 20:00:45');

-- 11번 게시글 댓글 14개 (삭제 댓글 1개)
INSERT INTO comment(comment_id, post_id, member_id, content, is_deleted, created_at)
values (7, 11, 1, '댓글 내용입니다.', false, '2023-08-30 10:00:00');

INSERT INTO comment(comment_id, post_id, member_id, content, is_deleted, created_at)
values (8, 11, 1, '삭제된 댓글 내용입니다.', true, '2023-08-30 11:00:00');

INSERT INTO comment(comment_id, post_id, member_id, content, is_deleted, created_at)
values (9, 11, 1, '댓글 내용입니다.', false, '2023-08-30 12:00:00');

INSERT INTO comment(comment_id, post_id, member_id, content, is_deleted, created_at)
values (10, 11, 1, '댓글 내용입니다.', false, '2023-08-30 13:00:00');

INSERT INTO comment(comment_id, post_id, member_id, content, is_deleted, created_at)
values (11, 11, 1, '댓글 내용입니다.', false, '2023-08-30 14:00:00');

INSERT INTO comment(comment_id, post_id, member_id, content, is_deleted, created_at)
values (12, 11, 1, '댓글 내용입니다.', false, '2023-08-30 15:00:00');

INSERT INTO comment(comment_id, post_id, member_id, content, is_deleted, created_at)
values (13, 11, 1, '댓글 내용입니다.', false, '2023-08-30 16:00:00');

INSERT INTO comment(comment_id, post_id, member_id, content, is_deleted, created_at)
values (14, 11, 1, '댓글 내용입니다.', false, '2023-08-30 17:00:00');

INSERT INTO comment(comment_id, post_id, member_id, content, is_deleted, created_at)
values (15, 11, 1, '댓글 내용입니다.', false, '2023-08-30 18:00:00');

INSERT INTO comment(comment_id, post_id, member_id, content, is_deleted, created_at)
values (16, 11, 1, '댓글 내용입니다.', false, '2023-08-30 19:00:00');

INSERT INTO comment(comment_id, post_id, member_id, content, is_deleted, created_at)
values (17, 11, 1, '댓글 내용입니다.', false, '2023-08-30 20:00:00');

INSERT INTO comment(comment_id, post_id, member_id, content, is_deleted, created_at)
values (18, 11, 1, '댓글 내용입니다.', false, '2023-08-30 21:00:00');

INSERT INTO comment(comment_id, post_id, member_id, content, is_deleted, created_at)
values (19, 11, 1, '댓글 내용입니다.', false, '2023-08-30 21:00:00');

INSERT INTO comment(comment_id, post_id, member_id, content, is_deleted, created_at)
values (20, 11, 1, '댓글 내용입니다.', false, '2023-08-30 21:00:00');

-- 2번 회사
INSERT INTO company(company_id, name) values(2, 'Kakao');

-- 2번 회사 게시글
INSERT INTO post (post_id, member_id, company_id, title, content, is_deleted, created_at)
values (15, 1, 2, '게시글 제목입니다.', '게시글 내용입니다. 10글자를 넘어갑니다.', false, '2023-08-29 19:00:00');

