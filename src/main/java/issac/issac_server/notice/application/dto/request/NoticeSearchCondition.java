package issac.issac_server.notice.application.dto.request;

import issac.issac_server.notice.domain.NoticeSource;
import issac.issac_server.user.domain.University;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NoticeSearchCondition {

    private University university;
    private List<NoticeSource> source;
    private String keyword;
}
