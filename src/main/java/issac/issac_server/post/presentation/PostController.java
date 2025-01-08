package issac.issac_server.post.presentation;

import issac.issac_server.auth.config.auth.Auth;
import issac.issac_server.post.application.PostService;
import issac.issac_server.post.application.dto.request.PostUpdateRequest;
import issac.issac_server.post.application.dto.request.PostCreateRequest;
import issac.issac_server.post.application.dto.response.PostPreviewResponse;
import issac.issac_server.post.application.dto.response.PostResponse;
import issac.issac_server.post.application.dto.request.PostSearchCondition;
import issac.issac_server.reaction.domain.ReactionType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@Secured({"ROLE_USER", "ROLE_STUDENT", "ROLE_TEACHING_ASSISTANT", "ROLE_PROFESSOR", "ROLE_ADMIN"})
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> savePost(
            @Auth Long userId,
            @RequestBody @Valid PostCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(userId, request));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> findPost(
            @Auth Long userId,
            @PathVariable Long postId
    ) {
        return ResponseEntity.ok(postService.find(userId, postId));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(
            @Auth Long userId,
            @PathVariable Long postId,
            @RequestBody @Valid PostUpdateRequest request
    ) {
        return ResponseEntity.ok(postService.update(userId, postId, request));
    }

    @GetMapping
    public ResponseEntity<Page<PostPreviewResponse>> findPosts(
            PostSearchCondition condition,
            Pageable pageable
    ) {
        return ResponseEntity.ok(postService.findPosts(condition, pageable));
    }

    @GetMapping("/me")
    public ResponseEntity<Page<PostPreviewResponse>> findMyPosts(
            @Auth Long userId,
            Pageable pageable
    ) {
        return ResponseEntity.ok(postService.findMyPosts(userId, pageable));
    }

    @GetMapping("/me/reactions")
    public ResponseEntity<Page<PostPreviewResponse>> findPostsByReaction(
            @Auth Long userId,
            @RequestParam ReactionType reactionType,
            Pageable pageable
    ) {
        return ResponseEntity.ok(postService.findPostsByReaction(userId, reactionType, pageable));
    }
}
