package com.growup.growthmate.query.repository.projection;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.growup.growthmate.community.comment.domain.QComment.comment;
import static com.growup.growthmate.community.post.domain.QPost.post;
import static com.growup.growthmate.member.domain.QMember.member;
import static com.querydsl.core.types.ExpressionUtils.count;

@AllArgsConstructor
@Getter
public class PostPreviewProjection {

    private static final int CONTENT_MIN_INDEX = 0;
    private static final int CONTENT_MAX_INDEX = 10;

    private static final Expression<Long> COMMENT_COUNT = ExpressionUtils.as(
            JPAExpressions.select(count(comment))
                    .from(comment)
                    .where(comment.postId.value.eq(post.id)), "commentCount");

    public static final ConstructorExpression<PostPreviewProjection> CONSTRUCTOR_EXPRESSION =
            Projections.constructor(PostPreviewProjection.class,
                    post.id,
                    member.name,
                    post.title.value,
                    post.content.value,
                    post.createdAt,
                    COMMENT_COUNT
            );

    private Long postId;
    private String name;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Long commentCount;

    public String getContent() {
        if (content.length() <= CONTENT_MAX_INDEX) {
            return content;
        }
        return this.content.substring(CONTENT_MIN_INDEX, CONTENT_MAX_INDEX);
    }
}
