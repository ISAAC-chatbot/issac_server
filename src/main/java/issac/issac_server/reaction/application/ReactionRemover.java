package issac.issac_server.reaction.application;

import issac.issac_server.reaction.application.dto.ReactionCreateRequest;
import issac.issac_server.reaction.domain.ReactionRepository;
import issac.issac_server.reaction.domain.ReactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReactionRemover {

    private final ReactionRepository reactionRepository;

    public void remove(Long reactionId) {
        reactionRepository.deleteById(reactionId);
    }

    public void removeOpposite(Long userId, ReactionCreateRequest request) {
        if (request.getType() == ReactionType.SCRAP) {
            return;
        }
        reactionRepository.deleteByUserIdAndTargetTypeAndTargetIdAndType(userId, request.getTargetType(), request.getTargetId(), request.getType().getOppositeType());
    }
}
