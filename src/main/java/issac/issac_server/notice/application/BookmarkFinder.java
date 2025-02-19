package issac.issac_server.notice.application;

import issac.issac_server.notice.domain.Bookmark;
import issac.issac_server.notice.domain.BookmarkRepository;
import issac.issac_server.notice.domain.NoticeSource;
import issac.issac_server.notice.exception.BookmarkErrorCode;
import issac.issac_server.notice.exception.BookmarkException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookmarkFinder {

    private final BookmarkRepository bookmarkRepository;

    public Bookmark find(Long bookmarkId) {
        return bookmarkRepository.findById(bookmarkId).orElseThrow(() -> new BookmarkException(BookmarkErrorCode.NOT_EXIST));
    }

    public List<Bookmark> findBookmarks(Long userId) {
        return bookmarkRepository.findAllByUserId(userId);
    }

    public Bookmark findByUserIdAndSource(Long userId, NoticeSource source) {
        return bookmarkRepository.findByUserIdAndSource(userId, source).orElseThrow(() -> new BookmarkException(BookmarkErrorCode.NOT_EXIST));
    }

    public boolean existsByUserIdAndSource(Long userId, NoticeSource source) {
        return bookmarkRepository.existsByUserIdAndSource(userId, source);
    }
}
