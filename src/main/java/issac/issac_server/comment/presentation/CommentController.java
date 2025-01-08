package issac.issac_server.comment.presentation;

import issac.issac_server.auth.config.auth.Auth;
import issac.issac_server.comment.application.CommentService;
import issac.issac_server.comment.application.dto.CommentCreateRequest;
import issac.issac_server.comment.application.dto.CommentResponse;
import issac.issac_server.comment.application.dto.CommentUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Secured({"ROLE_USER", "ROLE_STUDENT", "ROLE_TEACHING_ASSISTANT", "ROLE_PROFESSOR", "ROLE_ADMIN"})
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponse> save(
            @Auth Long userId,
            @PathVariable Long postId,
            @Valid @RequestBody CommentCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(userId, postId, request));
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<Slice<CommentResponse>> findComments(
            @PathVariable Long postId,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.findComments(postId, pageable));
    }

    @PutMapping("/posts/comments")
    public ResponseEntity<CommentResponse> update(
            @Auth Long userId,
            @Valid @RequestBody CommentUpdateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.update(userId, request));
    }

    @DeleteMapping("/posts/comments/{commentId}")
    public ResponseEntity<Void> delete(
            @Auth Long userId,
            @PathVariable Long commentId
    ) {
        commentService.delete(userId, commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
