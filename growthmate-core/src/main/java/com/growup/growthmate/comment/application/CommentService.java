package com.growup.growthmate.comment.application;

import com.growup.growthmate.comment.dto.CommentCreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    public Long create(CommentCreateCommand command) {
        return null;
    }
}
