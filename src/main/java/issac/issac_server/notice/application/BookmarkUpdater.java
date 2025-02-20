package issac.issac_server.notice.application;

import issac.issac_server.notice.application.dto.request.BookmarkUpdateRequest;
import issac.issac_server.notice.domain.Bookmark;
import issac.issac_server.notice.domain.NoticeSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookmarkUpdater {

    private final BookmarkRemover bookmarkRemover;
    private final BookmarkAppender bookmarkAppender;

    public List<Bookmark> update(Long userId, BookmarkUpdateRequest request, List<Bookmark> bookmarks) {

        List<NoticeSource> existingSources = Bookmark.extractSources(bookmarks);

        List<NoticeSource> requestSources = request.getSources();

        List<NoticeSource> newSources = requestSources.stream()
                .filter(source -> !existingSources.contains(source))
                .collect(Collectors.toList());

        List<NoticeSource> removedSources = existingSources.stream()
                .filter(source -> !requestSources.contains(source))
                .collect(Collectors.toList());

        bookmarkRemover.removeAll(userId, removedSources);

        return bookmarkAppender.appendAll(userId, newSources);
    }

    public Bookmark toggleNotification(Long userId, Bookmark bookmark) {
        bookmark.validateIsAuthor(userId);
        bookmark.toggleNotificationConsent();
        return bookmark;
    }
}
