package issac.issac_server.notice.presentation;

import issac.issac_server.notice.application.NoticeService;
import issac.issac_server.notice.application.dto.NoticePreviewResponse;
import issac.issac_server.notice.application.dto.NoticeSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notices")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public ResponseEntity<Page<NoticePreviewResponse>> search(NoticeSearchCondition condition, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(noticeService.search(condition, pageable));
    }
}
