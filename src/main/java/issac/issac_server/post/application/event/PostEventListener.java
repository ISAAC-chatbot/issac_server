package issac.issac_server.post.application.event;

import issac.issac_server.comment.application.CommentFinder;
import issac.issac_server.comment.application.event.CommentCreateEvent;
import issac.issac_server.comment.application.event.CommentRemoveEvent;
import issac.issac_server.post.application.PostUpdater;
import issac.issac_server.post.domain.Post;
import issac.issac_server.reaction.application.ReactionReader;
import issac.issac_server.reaction.domain.ReactionType;
import issac.issac_server.reaction.domain.TargetType;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.event.EventListener;
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

    private final JobLauncher jobLauncher;
    private final JobRegistry jobRegistry;

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
    public void updateCommentCount(CommentCreateEvent event) {
        Long count = commentFinder.count(event.getPostId());
        postUpdater.updateCommentCount(event.getPostId(), count);
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener
    public void updateCommentCount(CommentRemoveEvent event) {
        Long count = commentFinder.count(event.getPostId());
        postUpdater.updateCommentCount(event.getPostId(), count);
    }

    @Async
    @EventListener
    public void triggerNotificationJob(PostCreateEvent event) throws Exception {

        Post post = event.getPost();

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("entityId", post.getId().toString())
                .addString("university", post.getUniversity().toString())
                .addString("title", post.getTitle())
                .addString("content", post.getContent())
                .addString("entityType", TargetType.POST.toString())
                .addString("author", post.getAuthor().getProfile().getNickname())
                .toJobParameters();

        // 키워드 알림
        jobLauncher.run(jobRegistry.getJob("keywordJob"), jobParameters);
    }


}
