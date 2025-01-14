package issac.issac_server.comment.application.event;

import issac.issac_server.comment.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentCreateEvent {
    private Long postId;
    private Comment comment;
}

