package issac.issac_server.notice.presentation;

import issac.issac_server.auth.config.auth.Auth;
import issac.issac_server.notice.application.BookmarkService;
import issac.issac_server.notice.application.dto.request.BookmarkCreateRequest;
import issac.issac_server.notice.application.dto.response.BookmarkResponseV2;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/notices/bookmarks")
@Secured({"ROLE_USER", "ROLE_STUDENT", "ROLE_TEACHING_ASSISTANT", "ROLE_PROFESSOR", "ROLE_ADMIN"})
public class BookmarkControllerV2 {

    private final BookmarkService bookmarkService;

    @PutMapping
    public ResponseEntity<List<BookmarkResponseV2>> update(
            @Auth Long userId,
            @Valid @RequestBody BookmarkCreateRequest request
    ) {
        return ResponseEntity.ok(bookmarkService.updateV2(userId, request));
    }

    @GetMapping
    public ResponseEntity<List<BookmarkResponseV2>> search(@Auth Long userId) {
        return ResponseEntity.ok(bookmarkService.searchV2(userId));
    }

    @PatchMapping("/{bookmarkId}/notifications")
    public ResponseEntity<BookmarkResponseV2> toggleNotification(@Auth Long userId, @PathVariable Long bookmarkId) {
        return ResponseEntity.ok(bookmarkService.toggleNotification(userId, bookmarkId));
    }
}
