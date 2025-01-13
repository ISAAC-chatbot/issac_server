package issac.issac_server.notice.application.dto.request;

import issac.issac_server.notice.domain.NoticeSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkCreateRequest {
    private List<NoticeSource> sources;
}
