package issac.issac_server.notice.application;

import issac.issac_server.notice.domain.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookmarkRemover {

    private final BookmarkRepository bookmarkRepository;

    public void remove(Long userId) {
        bookmarkRepository.deleteAllByUserId(userId);
    }
}
