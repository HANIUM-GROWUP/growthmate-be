package com.growup.growthmate.post.domain;

import org.springframework.data.repository.Repository;

public interface PostRepository extends Repository<Post, Long> {
    Post save(Post post);
}
