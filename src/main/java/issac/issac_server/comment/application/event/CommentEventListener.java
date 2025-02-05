package issac.issac_server.comment.application.event;

import issac.issac_server.comment.application.CommentFinder;
import issac.issac_server.comment.application.CommentUpdater;
import issac.issac_server.comment.domain.Comment;
import issac.issac_server.device.application.DeviceTokenFinder;
import issac.issac_server.notification.application.FCMSender;
import issac.issac_server.notification.application.NotificationAppender;
import issac.issac_server.notification.application.dto.NotificationRequest;
import issac.issac_server.post.application.PostFinder;
import issac.issac_server.post.domain.Post;
import issac.issac_server.reaction.application.ReactionReader;
import issac.issac_server.reaction.domain.ReactionType;
import issac.issac_server.reaction.domain.TargetType;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CommentEventListener {

    private final ReactionReader reactionReader;
    private final CommentUpdater commentUpdater;
    private final PostFinder postFinder;

    private final FCMSender fcmSender;
    private final NotificationAppender notificationAppender;
    private final CommentFinder commentFinder;
    private final DeviceTokenFinder deviceTokenFinder;

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

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void notifyRelatedUsers(CommentCreateEvent event) {
        Post post = postFinder.find(event.getPostId());

        List<Long> userIdsToNotify = findUserIdsToNotify(event, post);

        Set<String> distinctTokens = deviceTokenFinder.findDistinctTokens(userIdsToNotify);

        NotificationRequest request = NotificationRequest.of(post, event.getComment());

        notificationAppender.appendAll(userIdsToNotify, request);

        fcmSender.send(request, distinctTokens);
    }

    private List<Long> findUserIdsToNotify(CommentCreateEvent event, Post post) {
        List<Long> userIdsToNotify = new ArrayList<>();

        userIdsToNotify.add(post.getAuthor().getId());

        if (event.getComment().getParent() != null) {
            Comment parent = event.getComment().getParent();
            userIdsToNotify.add(parent.getAuthor().getId());

            List<Comment> replies = commentFinder.findReplies(parent.getId());
            replies.forEach(reply -> userIdsToNotify.add(reply.getAuthor().getId()));
        }
        return userIdsToNotify;
    }

}
