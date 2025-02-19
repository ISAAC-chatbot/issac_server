package issac.issac_server.notice.application;

import issac.issac_server.notice.application.dto.request.BookmarkUpdateRequest;
import issac.issac_server.notice.application.dto.response.BookmarkResponse;
import issac.issac_server.notice.application.dto.response.BookmarkResponseV2;
import issac.issac_server.notice.domain.Bookmark;
import issac.issac_server.notice.domain.NoticeSource;
import issac.issac_server.notice.exception.BookmarkErrorCode;
import issac.issac_server.notice.exception.BookmarkException;
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
    private final BookmarkAppender bookmarkAppender;
    private final BookmarkRemover bookmarkRemover;

    @Transactional
    public BookmarkResponse update(Long userId, BookmarkUpdateRequest request) {
        List<Bookmark> bookmarks = bookmarkUpdater.update(userId, request);
        return new BookmarkResponse(Bookmark.extractSources(bookmarks));
    }

    public BookmarkResponse search(Long userId) {
        List<Bookmark> bookmarks = bookmarkFinder.findBookmarks(userId);
        return new BookmarkResponse(Bookmark.extractSources(bookmarks));
    }

    @Transactional
    public List<BookmarkResponseV2> updateV2(Long userId, BookmarkUpdateRequest request) {
        List<Bookmark> bookmarks = bookmarkUpdater.update(userId, request);
        return bookmarks.stream().map(BookmarkResponseV2::from).collect(Collectors.toList());
    }

    public List<BookmarkResponseV2> findBookmarks(Long userId) {
        List<Bookmark> bookmarks = bookmarkFinder.findBookmarks(userId);
        return bookmarks.stream().map(BookmarkResponseV2::from).collect(Collectors.toList());
    }

    @Transactional
    public BookmarkResponseV2 toggleNotification(Long userId, NoticeSource source) {
        Bookmark bookmark = bookmarkFinder.findByUserIdAndSource(userId, source);
        return BookmarkResponseV2.from(bookmarkUpdater.toggleNotification(userId, bookmark));
    }

    @Transactional
    public BookmarkResponseV2 save(Long userId, NoticeSource source) {
        if (bookmarkFinder.existsByUserIdAndSource(userId, source)) {
            throw new BookmarkException(BookmarkErrorCode.ALREADY_REGISTERED);
        }
        return BookmarkResponseV2.from(bookmarkAppender.append(userId, source));
    }

    @Transactional
    public void delete(Long userId, NoticeSource source) {
        bookmarkRemover.remove(userId, source);
    }
}
