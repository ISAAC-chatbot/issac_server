package issac.issac_server.reaction.application.dto;

import issac.issac_server.reaction.domain.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReactionResponse {
    private ReactionType reactionType;
    private boolean selected;
}
