package issac.issac_server.notice.presentation;

import issac.issac_server.auth.config.Auth;
import issac.issac_server.notice.application.BookmarkService;
import issac.issac_server.notice.application.dto.BookmarkCreateRequest;
import issac.issac_server.notice.application.dto.BookmarkResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notices/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PatchMapping
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
