package issac.issac_server.notice.presentation;

import issac.issac_server.auth.config.auth.Auth;
import issac.issac_server.notice.application.BookmarkService;
import issac.issac_server.notice.application.dto.request.BookmarkCreateRequest;
import issac.issac_server.notice.application.dto.request.BookmarkUpdateRequest;
import issac.issac_server.notice.application.dto.response.BookmarkResponseV2;
import issac.issac_server.notice.domain.NoticeSource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
            @Valid @RequestBody BookmarkUpdateRequest request
    ) {
        return ResponseEntity.ok(bookmarkService.update(userId, request));
    }

    @GetMapping
    public ResponseEntity<List<BookmarkResponseV2>> findBookmarks(@Auth Long userId) {
        return ResponseEntity.ok(bookmarkService.findBookmarks(userId));
    }

    @PatchMapping("/notifications")
    public ResponseEntity<BookmarkResponseV2> toggleNotification(@Auth Long userId, @RequestParam NoticeSource source) {
        return ResponseEntity.ok(bookmarkService.toggleNotification(userId, source));
    }

    @PostMapping
    public ResponseEntity<BookmarkResponseV2> save(@Auth Long userId, @RequestBody @Valid BookmarkCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookmarkService.save(userId, request.getSource()));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@Auth Long userId, @RequestParam NoticeSource source) {
        bookmarkService.delete(userId, source);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
