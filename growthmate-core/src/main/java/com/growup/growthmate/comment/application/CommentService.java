package com.growup.growthmate.comment.application;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.comment.domain.Comment;
import com.growup.growthmate.comment.domain.CommentRepository;
import com.growup.growthmate.comment.domain.value.CommentContent;
import com.growup.growthmate.comment.dto.CommentCreateCommand;
import com.growup.growthmate.comment.dto.CommentDeleteCommand;
import com.growup.growthmate.comment.dto.CommentMapper;
import com.growup.growthmate.comment.dto.CommentUpdateCommand;
import com.growup.growthmate.comment.exception.CommentException;
import com.growup.growthmate.post.domain.value.WriterId;
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

    public void update(CommentUpdateCommand command) {
        Comment comment = getComment(command);
        validateWriter(command, comment);
        comment.updateContent(new CommentContent(command.content()));
    }

    private Comment getComment(CommentUpdateCommand command) {
        CommentException exception = CommentException.NOT_FOUND_COMMENT;
        return commentRepository.findById(command.commentId())
                .orElseThrow(() -> new BusinessException(exception.getHttpStatusCode(), exception.getMessage()));
    }

    private void validateWriter(CommentUpdateCommand command, Comment comment) {
        CommentException exception = CommentException.UNAUTHORIZED_WRITER;
        if (!comment.isSameWriterId(new WriterId(command.writerId()))) {
            throw new BusinessException(exception.getHttpStatusCode(), exception.getMessage());
        }
    }

    public void delete(CommentDeleteCommand command) {

    }
}
