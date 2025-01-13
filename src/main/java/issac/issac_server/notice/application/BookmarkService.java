package issac.issac_server.notice.application;

import issac.issac_server.notice.application.dto.request.BookmarkCreateRequest;
import issac.issac_server.notice.application.dto.response.BookmarkResponse;
import issac.issac_server.notice.domain.Bookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRemover bookmarkRemover;
    private final BookmarkAppender bookmarkAppender;
    private final BookmarkFinder bookmarkFinder;

    @Transactional
    public BookmarkResponse update(Long userId, BookmarkCreateRequest request) {
        bookmarkRemover.remove(userId);
        List<Bookmark> bookmarks = bookmarkAppender.append(userId, request);
        return new BookmarkResponse(Bookmark.extractSources(bookmarks));
    }

    public BookmarkResponse search(Long userId) {
        List<Bookmark> bookmarks = bookmarkFinder.search(userId);
        return new BookmarkResponse(Bookmark.extractSources(bookmarks));
    }
}
