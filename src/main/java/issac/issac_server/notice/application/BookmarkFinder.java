package issac.issac_server.notice.application;

import issac.issac_server.notice.domain.Bookmark;
import issac.issac_server.notice.domain.BookmarkRepository;
import issac.issac_server.notice.domain.NoticeSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookmarkFinder {

    private final BookmarkRepository bookmarkRepository;

    public Optional<Bookmark> find(Long userId, NoticeSource source) {
        return bookmarkRepository.findByUserIdAndSource(userId, source);
    }

    public List<Bookmark> search(Long userId) {
        return bookmarkRepository.findAllByUserId(userId);
    }
}
