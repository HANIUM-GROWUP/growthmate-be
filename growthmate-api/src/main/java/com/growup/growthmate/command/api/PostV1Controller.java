package com.growup.growthmate.command.api;

import com.growup.growthmate.LoginMember;
import com.growup.growthmate.command.dto.PostCreateRequest;
import com.growup.growthmate.post.application.PostService;
import com.growup.growthmate.post.dto.PostCreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PostV1Controller {

    private final PostService postService;


    @PostMapping("/companies/{companyId}/posts")
    public ResponseEntity<Void> createPost(@PathVariable Long companyId,
                                           @RequestBody PostCreateRequest request,
                                           LoginMember loginMember) {
        PostCreateCommand command = new PostCreateCommand(
                companyId, loginMember.id(), request.title(), request.content()
        );
        Long postId = postService.create(command);
        return ResponseEntity.created(URI.create("/posts/" + postId))
                .build();
    }
}
