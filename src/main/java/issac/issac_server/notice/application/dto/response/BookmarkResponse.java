package issac.issac_server.notice.application.dto.response;

import issac.issac_server.notice.domain.NoticeSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkResponse {
    private List<NoticeSource> sources;
}
