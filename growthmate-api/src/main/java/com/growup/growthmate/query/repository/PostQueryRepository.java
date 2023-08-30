package com.growup.growthmate.query.repository;

import com.growup.growthmate.community.post.domain.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostQueryRepository extends Repository<Post, Long> {

    @Query(value = "select m.name, p.title, p.content, p.created_at, p.member_id " +
            "from POST p join MEMBER m ON p.member_id = m.member_id " +
            "WHERE p.post_id = :postId and p.is_deleted = false",
            nativeQuery = true)
    Optional<PostDetailProjection> findPostDetail(@Param("postId") Long postId);
}
