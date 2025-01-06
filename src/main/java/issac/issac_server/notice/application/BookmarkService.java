package issac.issac_server.notice.application;

import issac.issac_server.notice.application.dto.BookmarkCreateRequest;
import issac.issac_server.notice.domain.Bookmark;
import issac.issac_server.notice.domain.NoticeSource;
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
    public List<NoticeSource> update(Long userId, BookmarkCreateRequest request) {
        bookmarkRemover.remove(userId);
        List<Bookmark> bookmarks = bookmarkAppender.append(userId, request);
        return Bookmark.extractSources(bookmarks);
    }

    public List<NoticeSource> search(Long userId) {
        List<Bookmark> bookmarks = bookmarkFinder.search(userId);
        return Bookmark.extractSources(bookmarks);
    }
}
