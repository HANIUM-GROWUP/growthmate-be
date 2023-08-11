package com.growup.growthmate.command.api;

import com.growup.growthmate.LoginMember;
import com.growup.growthmate.command.dto.CommentCreateRequest;
import com.growup.growthmate.comment.application.CommentService;
import com.growup.growthmate.comment.dto.CommentCreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CommentV1Controller {

    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Void> createComment(@PathVariable Long postId,
                                              @RequestBody CommentCreateRequest request,
                                              LoginMember loginMember) {
        CommentCreateCommand command = new CommentCreateCommand(postId, request.content(), loginMember.id());
        Long commentId = commentService.create(command);
        return ResponseEntity.created(URI.create("/comments/" + commentId))
                .build();
    }
}
