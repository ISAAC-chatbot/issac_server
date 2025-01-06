package issac.issac_server.reaction.application;

import issac.issac_server.reaction.application.dto.ReactionCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReactionService {

    private final ReactionAppender reactionAppender;
    private final ReactionRemover reactionRemover;

    @Transactional
    public void save(Long userId, ReactionCreateRequest request) {
        reactionRemover.removeOpposite(userId, request);
        reactionAppender.append(userId, request);
    }
}
