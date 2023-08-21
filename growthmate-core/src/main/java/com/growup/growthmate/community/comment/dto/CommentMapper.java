package com.growup.growthmate.community.comment.dto;

import com.growup.growthmate.community.WriterId;
import com.growup.growthmate.community.comment.domain.Comment;
import com.growup.growthmate.community.comment.domain.value.CommentContent;
import com.growup.growthmate.community.comment.domain.value.PostId;
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
