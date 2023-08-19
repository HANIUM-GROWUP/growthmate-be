package com.growup.growthmate.community.comment.domain;

import com.growup.growthmate.community.CommunityBaseEntity;
import com.growup.growthmate.community.WriterId;
import com.growup.growthmate.community.comment.domain.value.CommentContent;
import com.growup.growthmate.community.comment.domain.value.PostId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SQLDelete(sql = "UPDATE comment set is_deleted = 1 where comment_id = ?")
@Where(clause = "is_deleted = 0")
public class Comment extends CommunityBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Embedded
    private PostId postId;

    @Embedded
    private CommentContent content;

    public Comment(PostId postId, WriterId writerId, CommentContent content) {
        super(writerId);
        this.postId = postId;
        this.content = content;
    }

    public void updateContent(CommentContent content) {
        this.content = content;
    }
}
