package issac.issac_server.comment.application.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentRemoveEvent {
    private Long postId;
}

