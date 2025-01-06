package issac.issac_server.reaction.application;

import issac.issac_server.reaction.application.dto.ReactionCreateRequest;
import issac.issac_server.reaction.domain.Reaction;
import issac.issac_server.reaction.domain.ReactionRepository;
import issac.issac_server.reaction.domain.ReactionType;
import issac.issac_server.reaction.domain.TargetType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReactionFinder {

    private final ReactionRepository reactionRepository;

    public Optional<Reaction> find(Long userId, ReactionCreateRequest request) {
        return reactionRepository.findByUserIdAndTargetTypeAndTargetIdAndType(userId, request.getTargetType(), request.getTargetId(), request.getType());
    }

    public Page<Reaction> find(Long userId, TargetType targetType, ReactionType type, Pageable pageable) {
        return reactionRepository.findByUserIdAndTargetTypeAndType(userId, targetType, type, pageable);
    }

    public boolean exists(Long userId, TargetType targetType, String targetId, ReactionType reactionType) {
        return reactionRepository.existsByUserIdAndTargetTypeAndTargetIdAndType(userId, targetType, targetId, reactionType);
    }
}
