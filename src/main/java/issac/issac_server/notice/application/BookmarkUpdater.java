package issac.issac_server.notice.application;

import issac.issac_server.notice.application.dto.request.BookmarkUpdateRequest;
import issac.issac_server.notice.domain.Bookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookmarkUpdater {

    private final BookmarkRemover bookmarkRemover;
    private final BookmarkAppender bookmarkAppender;

    public List<Bookmark> update(Long userId, BookmarkUpdateRequest request) {
        bookmarkRemover.removeAll(userId);
        return bookmarkAppender.appendAll(userId, request);
    }

    public Bookmark toggleNotification(Long userId, Bookmark bookmark) {
        bookmark.validateIsAuthor(userId);
        bookmark.toggleNotificationConsent();
        return bookmark;
    }
}
