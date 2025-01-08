package issac.issac_server.comment.application.event;

import issac.issac_server.reaction.domain.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentReactionEvent {
    private Long commentId;
    private ReactionType reactionType;
}
