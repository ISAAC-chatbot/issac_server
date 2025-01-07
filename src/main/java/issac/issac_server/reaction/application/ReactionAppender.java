package issac.issac_server.reaction.application;

import issac.issac_server.reaction.application.dto.ReactionCreateRequest;
import issac.issac_server.reaction.domain.Reaction;
import issac.issac_server.reaction.domain.ReactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReactionAppender {

    private final ReactionReader reactionReader;
    private final ReactionRemover reactionRemover;
    private final ReactionRepository reactionRepository;

    public void append(Long userId, ReactionCreateRequest request) {
        reactionReader.find(userId, request)
                .ifPresentOrElse(
                        reaction -> reactionRemover.remove(reaction.getId()),
                        () -> reactionRepository.save(Reaction.from(userId, request))
                );
    }
}
