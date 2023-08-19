package com.growup.growthmate.community.post.application;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.community.WriterId;
import com.growup.growthmate.community.WriterValidator;
import com.growup.growthmate.community.post.domain.Post;
import com.growup.growthmate.community.post.domain.PostRepository;
import com.growup.growthmate.community.post.domain.value.PostContent;
import com.growup.growthmate.community.post.domain.value.Title;
import com.growup.growthmate.community.post.dto.PostCreateCommand;
import com.growup.growthmate.community.post.dto.PostDeleteCommand;
import com.growup.growthmate.community.post.dto.PostMapper;
import com.growup.growthmate.community.post.dto.PostUpdateCommand;
import com.growup.growthmate.community.post.exception.PostException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final WriterValidator writerValidator;

    public Long create(PostCreateCommand command) {
        Post post = PostMapper.toDomain(command);
        Post saved = postRepository.save(post);
        return saved.getId();
    }

    public void update(PostUpdateCommand command) {
        Post post = getPost(command.postId());
        writerValidator.validate(post, new WriterId(command.writerId()));
        post.updateTitle(new Title(command.title()));
        post.updateContent(new PostContent(command.content()));
    }

    public void delete(PostDeleteCommand command) {
        Post post = getPost(command.postId());
        writerValidator.validate(post, new WriterId(command.writerId()));
        postRepository.delete(post);
    }

    private Post getPost(Long postId) {
        PostException notFound = PostException.NOT_FOUND_POST;
        return postRepository.findById(postId)
                .orElseThrow(() -> new BusinessException(notFound.getHttpStatusCode(), notFound.getMessage()));
    }
}
