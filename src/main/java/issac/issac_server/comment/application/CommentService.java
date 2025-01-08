package issac.issac_server.comment.application;

import issac.issac_server.comment.application.dto.CommentCreateRequest;
import issac.issac_server.comment.application.dto.CommentResponse;
import issac.issac_server.comment.application.dto.CommentUpdateRequest;
import issac.issac_server.comment.domain.Comment;
import issac.issac_server.post.application.PostFinder;
import issac.issac_server.post.domain.Post;
import issac.issac_server.user.application.UserFinder;
import issac.issac_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentAppender commentAppender;
    private final CommentFinder commentFinder;
    private final UserFinder userFinder;
    private final PostFinder postFinder;
    private final CommentUpdater commentUpdater;
    private final CommentRemover commentRemover;

    private final CommentEventHandler commentEventHandler;

    @Transactional
    public CommentResponse save(Long userId, Long postId, CommentCreateRequest request) {
        Post post = postFinder.find(postId);
        post.validatePostIsActive();
        User user = userFinder.find(userId);
        Comment comment = commentAppender.append(user, post.getId(), request);
        commentEventHandler.publish(comment.getPostId());
        return CommentResponse.from(comment);
    }

    public Slice<CommentResponse> findComments(Long postId, Pageable pageable) {
        return commentFinder.findAll(postId, pageable).map(CommentResponse::from);
    }

    @Transactional
    public CommentResponse update(Long userId, CommentUpdateRequest request) {
        User user = userFinder.find(userId);
        return commentUpdater.update(user, request);
    }

    @Transactional
    public void remove(Long userId, Long commentId) {
        User user = userFinder.find(userId);
        Comment comment = commentRemover.delete(user, commentId);
        commentEventHandler.publish(comment.getPostId());
    }
}
