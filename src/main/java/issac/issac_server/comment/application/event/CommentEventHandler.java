package issac.issac_server.comment.application.event;

import issac.issac_server.post.application.event.PostCommentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentEventHandler {

    private final ApplicationEventPublisher publisher;

    public void publish(Long postId) {
        publisher.publishEvent(new PostCommentEvent(postId));
    }
}
