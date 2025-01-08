package issac.issac_server.comment.application;

import issac.issac_server.comment.application.dto.CommentCreateRequest;
import issac.issac_server.comment.application.dto.CommentResponse;
import issac.issac_server.comment.domain.Comment;
import issac.issac_server.comment.domain.CommentRepository;
import issac.issac_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentAppender {

    private final CommentFinder commentFinder;
    private final CommentRepository commentRepository;

    public CommentResponse append(User user, Long postId, CommentCreateRequest request) {

        Comment comment = Comment.of(user, postId, request);

        if (request.getParentCommentId() != null) {
            Comment parent = commentFinder.find(request.getParentCommentId());
            parent.addReply(comment);
            comment.setParent(parent);
        }
        commentRepository.save(comment);

        return CommentResponse.from(comment);
    }


}
