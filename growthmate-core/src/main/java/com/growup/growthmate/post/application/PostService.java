package com.growup.growthmate.post.application;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.post.domain.Post;
import com.growup.growthmate.post.domain.PostRepository;
import com.growup.growthmate.post.domain.value.PostContent;
import com.growup.growthmate.post.domain.value.Title;
import com.growup.growthmate.post.domain.value.WriterId;
import com.growup.growthmate.post.dto.PostCreateCommand;
import com.growup.growthmate.post.dto.PostDeleteCommand;
import com.growup.growthmate.post.dto.PostMapper;
import com.growup.growthmate.post.dto.PostUpdateCommand;
import com.growup.growthmate.post.exception.PostException;
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

    public void update(PostUpdateCommand command) {
        Post post = getPost(command.postId());
        validateWriter(command.writerId(), post);
        post.updateTitle(new Title(command.title()));
        post.updateContent(new PostContent(command.content()));
    }

    public void delete(PostDeleteCommand command) {
        Post post = getPost(command.postId());
        validateWriter(command.writerId(), post);
        postRepository.delete(post);
    }

    private Post getPost(Long postId) {
        PostException notFound = PostException.NOT_FOUND_POST;
        return postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(notFound.getHttpStatusCode(), notFound.getMessage()));
    }

    private void validateWriter(Long writerId, Post post) {
        if (!post.isSameWriterId(new WriterId(writerId))) {
            PostException unauthorized = PostException.UNAUTHORIZED_WRITER;
            throw new BusinessException(unauthorized.getHttpStatusCode(), unauthorized.getMessage());
        }
    }
}
