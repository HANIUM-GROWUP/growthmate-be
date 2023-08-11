package com.growup.growthmate.comment.domain;

import com.growup.growthmate.comment.domain.value.CommentContent;
import com.growup.growthmate.comment.domain.value.PostId;
import com.growup.growthmate.post.domain.value.WriterId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Embedded
    private PostId postId;

    @Embedded
    private WriterId writerId;

    @Embedded
    private CommentContent content;

    public Comment(PostId postId, WriterId writerId, CommentContent content) {
        this.postId = postId;
        this.writerId = writerId;
        this.content = content;
    }
}
