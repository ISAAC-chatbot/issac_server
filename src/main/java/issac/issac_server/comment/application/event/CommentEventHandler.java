package issac.issac_server.comment.application.event;

import issac.issac_server.comment.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentEventHandler {

    private final ApplicationEventPublisher publisher;

    public void publish(Long postId, Comment comment) {
        publisher.publishEvent(new CommentCreateEvent(postId, comment));
    }

    public void publish(Long postId) {
        publisher.publishEvent(new CommentRemoveEvent(postId));
    }
}
