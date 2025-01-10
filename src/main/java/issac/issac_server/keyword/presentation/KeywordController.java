package issac.issac_server.keyword.presentation;

import issac.issac_server.auth.config.auth.Auth;
import issac.issac_server.keyword.application.KeywordService;
import issac.issac_server.keyword.application.dto.KeywordRequest;
import issac.issac_server.keyword.application.dto.KeywordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/keywords")
@RequiredArgsConstructor
public class KeywordController {

    private final KeywordService keywordService;

    @PostMapping
    public ResponseEntity<KeywordResponse> create(@Auth Long userId, @RequestBody KeywordRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(keywordService.create(userId, request));
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
