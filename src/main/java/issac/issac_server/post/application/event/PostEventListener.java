package issac.issac_server.post.application.event;

import issac.issac_server.comment.application.CommentFinder;
import issac.issac_server.post.application.PostUpdater;
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
    private final CommentFinder commentFinder;
    private final PostUpdater postUpdater;

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void updateLikeCount(PostLikeEvent event) {
        Long count = reactionReader.count(TargetType.POST, event.getPostId().toString(), ReactionType.LIKE);
        postUpdater.updateLikeCount(event.getPostId(), count);
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void updateCommentCount(PostLikeEvent event) {
        Long count = commentFinder.count(event.getPostId());
        postUpdater.updateCommentCount(event.getPostId(), count);
    }
}
