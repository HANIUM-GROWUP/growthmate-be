package com.growup.growthmate.query.repository;

import com.growup.growthmate.query.repository.projection.PostPreviewProjection;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.growup.growthmate.community.comment.domain.QComment.comment;
import static com.growup.growthmate.community.post.domain.QPost.post;
import static com.growup.growthmate.member.domain.QMember.member;
import static com.querydsl.core.types.ExpressionUtils.count;

@RequiredArgsConstructor
public class PostQueryDynamicRepositoryImpl implements PostQueryDynamicRepository {

    private static final Expression<Long> COMMENT_COUNT = ExpressionUtils.as(
            JPAExpressions.select(count(comment))
                    .from(comment)
                    .where(comment.postId.value.eq(post.id)), "commentCount");

    private static final ConstructorExpression<PostPreviewProjection> POST_PREVIEW_CONSTRUCTOR =
            Projections.constructor(PostPreviewProjection.class,
                    post.id,
                    member.name,
                    post.title.value,
                    post.content.value,
                    post.createdAt,
                    COMMENT_COUNT
            );

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PostPreviewProjection> findPostPreviews(Long companyId, Long cursor, Integer size) {
        BooleanBuilder ifCursorPresent = DynamicBooleanBuilder.builder()
                .and(() -> post.id.lt(cursor))
                .build();
        return queryFactory.select(POST_PREVIEW_CONSTRUCTOR)
                .from(post)
                .join(member).on(post.writerId.value.eq(member.id))
                .where(post.companyId.value.eq(companyId)
                        .and(ifCursorPresent))
                .orderBy(post.id.desc())
                .limit(size)
                .fetch();
    }
}
