package issac.issac_server.notice.application;

import issac.issac_server.notice.application.dto.request.BookmarkCreateRequest;
import issac.issac_server.notice.application.dto.response.BookmarkResponse;
import issac.issac_server.notice.application.dto.response.BookmarkResponseV2;
import issac.issac_server.notice.domain.Bookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkFinder bookmarkFinder;
    private final BookmarkUpdater bookmarkUpdater;

    @Transactional
    public BookmarkResponse update(Long userId, BookmarkCreateRequest request) {
        List<Bookmark> bookmarks = bookmarkUpdater.update(userId, request);
        return new BookmarkResponse(Bookmark.extractSources(bookmarks));
    }

    public BookmarkResponse search(Long userId) {
        List<Bookmark> bookmarks = bookmarkFinder.search(userId);
        return new BookmarkResponse(Bookmark.extractSources(bookmarks));
    }

    @Transactional
    public List<BookmarkResponseV2> updateV2(Long userId, BookmarkCreateRequest request) {
        List<Bookmark> bookmarks = bookmarkUpdater.update(userId, request);
        return bookmarks.stream().map(BookmarkResponseV2::from).collect(Collectors.toList());
    }

    public List<BookmarkResponseV2> searchV2(Long userId) {
        List<Bookmark> bookmarks = bookmarkFinder.search(userId);
        return bookmarks.stream().map(BookmarkResponseV2::from).collect(Collectors.toList());
    }

    @Transactional
    public BookmarkResponseV2 toggleNotification(Long userId, Long bookmarkId) {
        Bookmark bookmark = bookmarkFinder.find(bookmarkId);
        return BookmarkResponseV2.from(bookmarkUpdater.toggleNotification(userId, bookmark));
    }
}
