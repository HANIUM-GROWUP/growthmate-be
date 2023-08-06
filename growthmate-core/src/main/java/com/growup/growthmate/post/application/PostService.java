package com.growup.growthmate.post.application;

import com.growup.growthmate.post.domain.Post;
import com.growup.growthmate.post.domain.PostRepository;
import com.growup.growthmate.post.dto.PostCreateCommand;
import com.growup.growthmate.post.dto.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Long create(PostCreateCommand command) {
        Post post = PostMapper.toDomain(command);
        Post saved = postRepository.save(post);
        return saved.getId();
    }
}
