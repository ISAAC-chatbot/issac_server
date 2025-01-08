package issac.issac_server.post.application.dto.response;

import issac.issac_server.reaction.domain.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReactionStatusResponse {

    private ReactionType reactionType;
    private long count;
    private boolean selected;

    public static ReactionStatusResponse of(ReactionType reactionType, long count, boolean selected) {
        return new ReactionStatusResponse(reactionType, count, selected);
    }

}
