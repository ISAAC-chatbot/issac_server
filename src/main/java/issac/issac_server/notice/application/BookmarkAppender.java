package issac.issac_server.notice.application;

import issac.issac_server.notice.application.dto.BookmarkCreateRequest;
import issac.issac_server.notice.domain.Bookmark;
import issac.issac_server.notice.domain.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookmarkAppender {

    private final BookmarkRepository bookmarkRepository;

    public List<Bookmark> append(Long userId, BookmarkCreateRequest request) {
        return bookmarkRepository.saveAll(Bookmark.of(userId, request.getSources()));
    }
}
