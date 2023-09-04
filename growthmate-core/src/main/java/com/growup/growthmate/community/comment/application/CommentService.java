package com.growup.growthmate.community.comment.application;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.community.WriterId;
import com.growup.growthmate.community.WriterValidator;
import com.growup.growthmate.community.comment.domain.Comment;
import com.growup.growthmate.community.comment.domain.CommentRepository;
import com.growup.growthmate.community.comment.domain.value.CommentContent;
import com.growup.growthmate.community.comment.dto.CommentCreateCommand;
import com.growup.growthmate.community.comment.dto.CommentDeleteCommand;
import com.growup.growthmate.community.comment.dto.CommentMapper;
import com.growup.growthmate.community.comment.dto.CommentUpdateCommand;
import com.growup.growthmate.community.comment.exception.CommentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final WriterValidator writerValidator;

    public Long create(CommentCreateCommand command) {
        Comment comment = CommentMapper.toDomain(command);
        Comment saved = commentRepository.save(comment);
        return saved.getId();
    }

    public void update(CommentUpdateCommand command) {
        Comment comment = getComment(command.commentId());
        writerValidator.validate(comment, new WriterId(command.writerId()));
        comment.updateContent(new CommentContent(command.content()));
    }

    public void delete(CommentDeleteCommand command) {
        Comment comment = getComment(command.commentId());
        writerValidator.validate(comment, new WriterId(command.writerId()));
        commentRepository.delete(comment);
    }

    private Comment getComment(Long commentId) {
        CommentException exception = CommentException.NOT_FOUND_COMMENT;
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessException(exception.getHttpStatusCode(), exception.getMessage()));
    }
}
