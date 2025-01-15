package issac.issac_server.notice.application.event;

import issac.issac_server.notice.application.dto.request.NoticeCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoticeSaveEvent {
    private String noticeId;
    private NoticeCreateRequest request;
}
