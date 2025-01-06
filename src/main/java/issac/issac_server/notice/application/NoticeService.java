package issac.issac_server.notice.application;

import issac.issac_server.notice.application.dto.NoticePreviewResponse;
import issac.issac_server.notice.application.dto.NoticeResponse;
import issac.issac_server.notice.application.dto.NoticeSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeFinder noticeFinder;

    public Page<NoticePreviewResponse> search(NoticeSearchCondition condition, Pageable pageable) {
        return noticeFinder.search(condition, pageable);
    }

    public NoticeResponse find(String noticeId) {
        return noticeFinder.find(noticeId);
    }
}
