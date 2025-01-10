package issac.issac_server.keyword.presentation;

import issac.issac_server.auth.config.auth.Auth;
import issac.issac_server.keyword.application.KeywordService;
import issac.issac_server.keyword.application.dto.KeywordRequest;
import issac.issac_server.keyword.application.dto.KeywordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/keywords")
@RequiredArgsConstructor
@Secured({"ROLE_USER", "ROLE_STUDENT", "ROLE_TEACHING_ASSISTANT", "ROLE_PROFESSOR", "ROLE_ADMIN"})
public class KeywordController {

    private final KeywordService keywordService;

    @PostMapping
    public ResponseEntity<KeywordResponse> save(@Auth Long userId, @RequestBody KeywordRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(keywordService.save(userId, request));
    }

    @GetMapping
    public ResponseEntity<List<KeywordResponse>> findKeywords(@Auth Long userId) {
        return ResponseEntity.ok(keywordService.findKeywords(userId));
    }

    @DeleteMapping("/{keywordId}")
    public ResponseEntity<Void> remove(@Auth Long userId, @PathVariable Long keywordId) {
        keywordService.remove(userId, keywordId);
        return ResponseEntity.noContent().build();
    }
}
