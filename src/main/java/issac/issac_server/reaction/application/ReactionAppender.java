package issac.issac_server.reaction.application;

import issac.issac_server.reaction.application.dto.ReactionCreateRequest;
import issac.issac_server.reaction.domain.Reaction;
import issac.issac_server.reaction.domain.ReactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReactionAppender {

    private final ReactionReader reactionReader;
    private final ReactionRemover reactionRemover;
    private final ReactionRepository reactionRepository;

    public Optional<Reaction> append(Long userId, ReactionCreateRequest request) {
        return reactionReader.find(userId, request)
                .map(reaction -> {
                    reactionRemover.remove(reaction.getId());
                    return Optional.<Reaction>empty();
                })
                .orElseGet(() -> Optional.of(reactionRepository.save(Reaction.from(userId, request))));
    }


}
