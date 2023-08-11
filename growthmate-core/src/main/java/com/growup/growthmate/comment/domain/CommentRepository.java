package com.growup.growthmate.comment.domain;

import org.springframework.data.repository.Repository;

public interface CommentRepository extends Repository<Comment, Long> {

    Comment save(Comment comment);
}
