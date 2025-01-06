package issac.issac_server.notice.application;

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

    public Page<NoticeResponse> search(NoticeSearchCondition condition, Pageable pageable) {
        return noticeFinder.search(condition, pageable);
    }
}
