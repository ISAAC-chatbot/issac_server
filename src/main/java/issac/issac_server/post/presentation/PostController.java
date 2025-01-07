package issac.issac_server.post.presentation;

import issac.issac_server.auth.config.Auth;
import issac.issac_server.post.application.PostService;
import issac.issac_server.post.application.PostUpdateRequest;
import issac.issac_server.post.application.dto.PostCreateRequest;
import issac.issac_server.post.application.dto.PostResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
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
}
