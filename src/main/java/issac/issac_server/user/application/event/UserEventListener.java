package issac.issac_server.user.application.event;

import issac.issac_server.auth.application.RefreshTokenRemover;
import issac.issac_server.device.application.DeviceTokenRemover;
import issac.issac_server.keyword.application.KeywordRemover;
import issac.issac_server.notice.application.BookmarkRemover;
import issac.issac_server.reaction.application.ReactionRemover;
import issac.issac_server.user.application.UserRevokeAppender;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class UserEventListener {

    private final RefreshTokenRemover refreshTokenRemover;
    private final DeviceTokenRemover deviceTokenRemover;
    private final BookmarkRemover bookmarkRemover;
    private final KeywordRemover keywordRemover;
    private final ReactionRemover reactionRemover;

    private final UserRevokeAppender userRevokeAppender;

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void deleteUserInfo(UserRevokeEvent event) {
        refreshTokenRemover.remove(event.getUserId());
        deviceTokenRemover.remove(event.getUserId());
        bookmarkRemover.removeAll(event.getUserId());
        keywordRemover.removeAll(event.getUserId());
        reactionRemover.removeAllScrap(event.getUserId());
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void saveRevokeReason(UserRevokeEvent event) {
        userRevokeAppender.append(event.getUserId(), event.getReasonType(), event.getReasonDescription());
    }
}
