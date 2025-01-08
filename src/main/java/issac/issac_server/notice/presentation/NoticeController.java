package issac.issac_server.notice.presentation;

import issac.issac_server.auth.config.auth.Auth;
import issac.issac_server.notice.application.NoticeService;
import issac.issac_server.notice.application.dto.NoticePreviewResponse;
import issac.issac_server.notice.application.dto.NoticeResponse;
import issac.issac_server.notice.application.dto.NoticeSearchCondition;
import issac.issac_server.reaction.domain.ReactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notices")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public ResponseEntity<Page<NoticePreviewResponse>> search(NoticeSearchCondition condition, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(noticeService.search(condition, pageable));
    }

    @GetMapping("/{noticeId}")
    public ResponseEntity<NoticeResponse> find(
            @Auth Long userId,
            @PathVariable String noticeId) {
        return ResponseEntity.status(HttpStatus.OK).body(noticeService.find(userId, noticeId));
    }

    @GetMapping("/me/reactions")
    @Secured({"ROLE_USER", "ROLE_STUDENT", "ROLE_TEACHING_ASSISTANT", "ROLE_PROFESSOR", "ROLE_ADMIN"})
    public ResponseEntity<Page<NoticePreviewResponse>> findNoticesByReaction(
            @Auth Long userId,
            @RequestParam ReactionType reactionType,
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(noticeService.findNoticesByReaction(userId, reactionType, pageable));
    }
}
