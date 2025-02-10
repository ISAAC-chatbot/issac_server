package issac.issac_server.reaction.application;

import issac.issac_server.reaction.application.dto.ReactionCreateRequest;
import issac.issac_server.reaction.application.dto.ReactionResponse;
import issac.issac_server.reaction.domain.Reaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReactionService {

    private final ReactionAppender reactionAppender;
    private final ReactionRemover reactionRemover;
    private final ReactionEventHandler reactionEventHandler;

    @Transactional
    public ReactionResponse save(Long userId, ReactionCreateRequest request) {
        reactionRemover.removeOpposite(userId, request);
        Optional<Reaction> reaction = reactionAppender.append(userId, request);
        reactionEventHandler.publish(request);
        return new ReactionResponse(request.getType(), reaction.isPresent());
    }
}
