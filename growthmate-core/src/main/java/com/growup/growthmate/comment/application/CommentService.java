package com.growup.growthmate.comment.application;

import com.growup.growthmate.comment.domain.Comment;
import com.growup.growthmate.comment.domain.CommentRepository;
import com.growup.growthmate.comment.dto.CommentCreateCommand;
import com.growup.growthmate.comment.dto.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Long create(CommentCreateCommand command) {
        Comment comment = CommentMapper.toDomain(command);
        Comment saved = commentRepository.save(comment);
        return saved.getId();
    }
}
