package com.growup.growthmate.community.comment.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CommentRepository extends Repository<Comment, Long> {

    Comment save(Comment comment);

    Optional<Comment> findById(Long id);

    void delete(Comment comment);
}
