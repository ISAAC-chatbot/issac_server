package issac.issac_server.comment.application;

import issac.issac_server.comment.domain.Comment;
import issac.issac_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentRemover {

    private final CommentFinder commentFinder;

    public Comment delete(User user, Long commentId) {
        Comment comment = commentFinder.find(commentId);
        comment.validateIsAuthor(user);
        comment.delete();
        return comment;
    }
}
