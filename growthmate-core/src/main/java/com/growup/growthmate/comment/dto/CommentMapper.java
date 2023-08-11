package com.growup.growthmate.comment.dto;

import com.growup.growthmate.comment.domain.Comment;
import com.growup.growthmate.comment.domain.value.CommentContent;
import com.growup.growthmate.comment.domain.value.PostId;
import com.growup.growthmate.post.domain.value.WriterId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentMapper {

    public static Comment toDomain(CommentCreateCommand command) {
        return new Comment(
                new PostId(command.postId()),
                new WriterId(command.writerId()),
                new CommentContent(command.content())
        );
    }
}
