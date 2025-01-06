package issac.issac_server.reaction.application;

import issac.issac_server.reaction.application.dto.ReactionCreateRequest;
import issac.issac_server.reaction.domain.Reaction;
import issac.issac_server.reaction.domain.ReactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReactionFinder {

    private final ReactionRepository reactionRepository;

    public Optional<Reaction> find(Long userId, ReactionCreateRequest request) {
        return reactionRepository.findByUserIdAndTargetTypeAndTargetIdAndType(userId, request.getTargetType(), request.getTargetId(), request.getType());
    }


}
