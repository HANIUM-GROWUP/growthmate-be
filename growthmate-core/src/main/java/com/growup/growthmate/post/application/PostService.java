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
        Post post = validateAndGet(command.postId(), command.writerId());
        post.updateTitle(new Title(command.title()));
        post.updateContent(new PostContent(command.content()));
    }

    public void delete(PostDeleteCommand command) {
        Post post = validateAndGet(command.postId(), command.writerId());
        postRepository.delete(post);
    }

    private Post validateAndGet(Long postId, Long writerId) {
        PostException notFound = PostException.NOT_FOUND_POST;
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(notFound.getHttpStatusCode(), notFound.getMessage()));
        if (!post.isSameWriterId(new WriterId(writerId))) {
            PostException unauthorized = PostException.UNAUTHORIZED_WRITER;
            throw new BusinessException(unauthorized.getHttpStatusCode(), unauthorized.getMessage());
        }
        return post;
    }
}
