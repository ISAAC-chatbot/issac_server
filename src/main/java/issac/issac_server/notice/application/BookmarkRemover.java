package issac.issac_server.notice.application;

import issac.issac_server.notice.domain.BookmarkRepository;
import issac.issac_server.notice.domain.NoticeSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookmarkRemover {

    private final BookmarkRepository bookmarkRepository;

    public void removeAll(Long userId) {
        bookmarkRepository.deleteAllByUserId(userId);
    }

    public void removeAll(Long userId, List<NoticeSource> sources) {
        bookmarkRepository.deleteAllByUserIdAndSourceIn(userId, sources);
    }

    public void remove(Long userId, NoticeSource source) {
        bookmarkRepository.deleteByUserIdAndSource(userId, source);
    }
}
