package issac.issac_server.notice.application;

import issac.issac_server.notice.domain.Bookmark;
import issac.issac_server.notice.domain.BookmarkRepository;
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
        return bookmarkRepository.findById(bookmarkId).orElseThrow(()-> new BookmarkException(BookmarkErrorCode.NOT_EXIST));
    }

    public List<Bookmark> search(Long userId) {
        return bookmarkRepository.findAllByUserId(userId);
    }
}
