package issac.issac_server.reaction.application;

import issac.issac_server.post.application.event.PostLikeEvent;
import issac.issac_server.reaction.application.dto.ReactionCreateRequest;
import issac.issac_server.reaction.domain.ReactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReactionEventHandler {

    private final ApplicationEventPublisher publisher;

    public void publish(ReactionCreateRequest request) {
        if (request.getType() == ReactionType.SCRAP) {
            return;
        }
        switch (request.getTargetType()) {
            case POST:
                publisher.publishEvent(new PostLikeEvent(Long.valueOf(request.getTargetId())));
        }
    }
}
