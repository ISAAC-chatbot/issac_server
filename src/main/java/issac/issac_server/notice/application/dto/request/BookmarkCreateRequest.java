package issac.issac_server.notice.application.dto.request;

import issac.issac_server.notice.domain.NoticeSource;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkCreateRequest {
    @NotNull
    private NoticeSource source;
}
