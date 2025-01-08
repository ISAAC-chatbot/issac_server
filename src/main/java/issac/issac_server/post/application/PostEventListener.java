package issac.issac_server.post.application;

import issac.issac_server.post.application.event.PostLikeEvent;
import issac.issac_server.reaction.application.ReactionReader;
import issac.issac_server.reaction.domain.ReactionType;
import issac.issac_server.reaction.domain.TargetType;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class PostEventListener {

    private final ReactionReader reactionReader;
    private final PostUpdater postUpdater;

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void updateLikeCount(PostLikeEvent event) {
        Long count = reactionReader.count(TargetType.POST, event.getPostId().toString(), ReactionType.LIKE);
        postUpdater.updateLikeCount(event.getPostId(), count);
    }
}
