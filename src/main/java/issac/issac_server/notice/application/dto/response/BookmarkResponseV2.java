package issac.issac_server.notice.application.dto.response;

import issac.issac_server.notice.domain.Bookmark;
import issac.issac_server.notice.domain.NoticeSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkResponseV2 {

    private NoticeSource source;
    private boolean notificationConsent;

    public static BookmarkResponseV2 from(Bookmark bookmark) {
        return new BookmarkResponseV2(bookmark.getSource(), bookmark.isNotificationConsent());
    }

}
