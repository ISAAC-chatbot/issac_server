package issac.issac_server.user.application.event;

import issac.issac_server.notice.application.BookmarkAppender;
import issac.issac_server.notice.domain.NoticeSource;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class ProfileEventListener {

    private final BookmarkAppender bookmarkAppender;

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void updateAcademicNoticeBookmark(ProfileSaveEvent event) {
        bookmarkAppender.append(event.getUserId(), NoticeSource.ACADEMIC_NOTICE);
    }
}
