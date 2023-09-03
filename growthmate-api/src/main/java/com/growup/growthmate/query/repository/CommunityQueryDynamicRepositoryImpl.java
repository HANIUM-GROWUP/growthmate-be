package com.growup.growthmate.query.repository;

import com.growup.growthmate.query.repository.projection.CommentProjection;
import com.growup.growthmate.query.repository.projection.PostPreviewProjection;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.growup.growthmate.community.comment.domain.QComment.comment;
import static com.growup.growthmate.community.post.domain.QPost.post;
import static com.growup.growthmate.member.domain.QMember.member;

@RequiredArgsConstructor
public class CommunityQueryDynamicRepositoryImpl implements CommunityQueryDynamicRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PostPreviewProjection> findPostPreviews(Long companyId, Long cursor, Integer size) {
        return queryFactory.select(PostPreviewProjection.CONSTRUCTOR_EXPRESSION)
                .from(post)
                .join(member).on(post.writerId.value.eq(member.id))
                .where(post.companyId.value.eq(companyId)
                        .and(getCursorCondition(cursor, post.id)))
                .orderBy(post.id.desc())
                .limit(size)
                .fetch();
    }

    @Override
    public List<CommentProjection> findComments(Long postId, Long cursor, Integer size) {
        return queryFactory.select(CommentProjection.CONSTRUCTOR_EXPRESSION)
                .from(comment)
                .join(member).on(comment.writerId.value.eq(member.id))
                .where(comment.postId.value.eq(postId)
                        .and(getCursorCondition(cursor, comment.id)))
                .orderBy(comment.id.desc())
                .limit(size)
                .fetch();
    }

    private BooleanBuilder getCursorCondition(Long cursor, NumberPath<Long> id) {
        if (cursor == null) {
            return new BooleanBuilder();
        }
        return new BooleanBuilder(id.lt(cursor));
    }
}
