package issac.issac_server.comment.application.event;

import issac.issac_server.comment.application.CommentUpdater;
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
public class CommentEventListener {

    private final ReactionReader reactionReader;
    private final CommentUpdater commentUpdater;

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void updateReactionCount(CommentReactionEvent event) {

        switch (event.getReactionType()) {
            case LIKE:
                Long likeCount = reactionReader.count(TargetType.COMMENT, event.getCommentId().toString(), ReactionType.LIKE);
                commentUpdater.updateLikeCount(event.getCommentId(), likeCount);
                break;
            case UNLIKE:
                Long unlikeCount = reactionReader.count(TargetType.COMMENT, event.getCommentId().toString(), ReactionType.UNLIKE);
                commentUpdater.updateUnlikeCount(event.getCommentId(), unlikeCount);
                break;

        }
    }
}
