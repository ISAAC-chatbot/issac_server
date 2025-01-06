package issac.issac_server.notice.application.dto;

import issac.issac_server.notice.domain.NoticeSource;
import lombok.Getter;

import java.util.List;

@Getter
public class BookmarkCreateRequest {
    private List<NoticeSource> sources;
}
