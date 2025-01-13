package issac.issac_server.notice.application.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoticeSaveEvent {
    private String noticeId;
}
