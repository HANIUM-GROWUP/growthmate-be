package com.growup.growthmate.command.api;

import com.growup.growthmate.LoginMember;
import com.growup.growthmate.command.dto.CommentCreateRequest;
import com.growup.growthmate.command.dto.CommentUpdateRequest;
import com.growup.growthmate.community.comment.application.CommentService;
import com.growup.growthmate.community.comment.dto.CommentCreateCommand;
import com.growup.growthmate.community.comment.dto.CommentDeleteCommand;
import com.growup.growthmate.community.comment.dto.CommentUpdateCommand;
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

    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable Long commentId,
                                              @RequestBody CommentUpdateRequest request,
                                              LoginMember loginMember) {
        CommentUpdateCommand command = new CommentUpdateCommand(commentId, request.content(), loginMember.id());
        commentService.update(command);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId,
                                              LoginMember loginMember) {
        CommentDeleteCommand command = new CommentDeleteCommand(commentId, loginMember.id());
        commentService.delete(command);
        return ResponseEntity.noContent().build();
    }
}
