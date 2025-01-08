package issac.issac_server.reaction.application;

import issac.issac_server.post.application.event.PostLikeEvent;
import issac.issac_server.reaction.application.dto.ReactionCreateRequest;
import issac.issac_server.reaction.domain.ReactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReactionService {

    private final ReactionAppender reactionAppender;
    private final ReactionRemover reactionRemover;
    private final ReactionEventHandler reactionEventHandler;

    @Transactional
    public void save(Long userId, ReactionCreateRequest request) {
        reactionRemover.removeOpposite(userId, request);
        reactionAppender.append(userId, request);
        reactionEventHandler.publish(request);
    }
}
