package issac.issac_server.notice.presentation;

import issac.issac_server.auth.config.auth.Auth;
import issac.issac_server.notice.application.BookmarkService;
import issac.issac_server.notice.application.dto.BookmarkCreateRequest;
import issac.issac_server.notice.application.dto.BookmarkResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notices/bookmarks")
@Secured({"ROLE_USER", "ROLE_STUDENT", "ROLE_TEACHING_ASSISTANT", "ROLE_PROFESSOR", "ROLE_ADMIN"})
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PutMapping
    public ResponseEntity<BookmarkResponse> update(
            @Auth Long userId,
            @Valid @RequestBody BookmarkCreateRequest request
    ) {
        return ResponseEntity.ok(bookmarkService.update(userId, request));
    }

    @GetMapping
    public ResponseEntity<BookmarkResponse> search(@Auth Long userId) {
        return ResponseEntity.ok(bookmarkService.search(userId));
    }
}
