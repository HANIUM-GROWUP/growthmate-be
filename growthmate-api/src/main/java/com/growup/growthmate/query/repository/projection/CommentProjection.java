package com.growup.growthmate.query.repository.projection;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.growup.growthmate.community.comment.domain.QComment.comment;
import static com.growup.growthmate.member.domain.QMember.member;

@AllArgsConstructor
@Getter
public class CommentProjection {

    public static final ConstructorExpression<CommentProjection> CONSTRUCTOR_EXPRESSION =
            Projections.constructor(CommentProjection.class,
                    comment.id,
                    member.name,
                    comment.content.value,
                    member.id
            );

    private Long commentId;
    private String name;
    private String content;
    private Long memberId;
}
