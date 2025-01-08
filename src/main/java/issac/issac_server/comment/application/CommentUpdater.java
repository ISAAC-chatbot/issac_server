package issac.issac_server.comment.application;

import issac.issac_server.comment.application.dto.CommentResponse;
import issac.issac_server.comment.application.dto.CommentUpdateRequest;
import issac.issac_server.comment.domain.Comment;
import issac.issac_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentUpdater {

    private final CommentFinder commentFinder;

    public CommentResponse update(User user, CommentUpdateRequest request) {
        Comment comment = commentFinder.find(request.getCommentId());
        comment.validateIsAuthor(user);
        comment.update(request.getContent());
        return CommentResponse.from(comment);
    }
}
