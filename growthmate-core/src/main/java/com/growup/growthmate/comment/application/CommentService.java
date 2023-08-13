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
        Comment comment = getComment(command.commentId());
        validateWriter(comment, command.writerId());
        comment.updateContent(new CommentContent(command.content()));
    }

    public void delete(CommentDeleteCommand command) {
        Comment comment = getComment(command.commentId());
        validateWriter(comment, command.writerId());
        commentRepository.delete(comment);
    }

    private Comment getComment(Long commentId) {
        CommentException exception = CommentException.NOT_FOUND_COMMENT;
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessException(exception.getHttpStatusCode(), exception.getMessage()));
    }

    private void validateWriter(Comment comment, Long writerId) {
        CommentException exception = CommentException.UNAUTHORIZED_WRITER;
        if (!comment.isSameWriterId(new WriterId(writerId))) {
            throw new BusinessException(exception.getHttpStatusCode(), exception.getMessage());
        }
    }
}
