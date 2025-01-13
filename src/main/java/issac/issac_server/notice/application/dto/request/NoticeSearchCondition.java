package issac.issac_server.notice.application.dto.request;

import issac.issac_server.notice.domain.NoticeSource;
import issac.issac_server.user.domain.University;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeSearchCondition {

    private University university;
    private NoticeSource source;
    private String keyword;
}
