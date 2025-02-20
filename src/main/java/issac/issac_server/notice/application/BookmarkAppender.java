package issac.issac_server.notice.application;

import issac.issac_server.notice.domain.Bookmark;
import issac.issac_server.notice.domain.BookmarkRepository;
import issac.issac_server.notice.domain.NoticeSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookmarkAppender {

    private final BookmarkRepository bookmarkRepository;

    public List<Bookmark> appendAll(Long userId, List<NoticeSource> sources) {
        return bookmarkRepository.saveAll(Bookmark.of(userId, sources));
    }

    public Bookmark append(Long userId, NoticeSource source) {
        return bookmarkRepository.save(Bookmark.of(userId, source));
    }
}
