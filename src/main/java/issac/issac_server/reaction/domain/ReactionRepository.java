package issac.issac_server.reaction.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {

    Optional<Reaction> findByUserIdAndTargetTypeAndTargetIdAndType(Long userId, TargetType targetType, String targetId, ReactionType reactionType);

    void deleteByUserIdAndTargetTypeAndTargetIdAndType(Long userId, TargetType targetType, String targetId, ReactionType reactionType);
}
