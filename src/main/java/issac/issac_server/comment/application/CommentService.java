package issac.issac_server.comment.application;

import issac.issac_server.comment.application.dto.CommentCreateRequest;
import issac.issac_server.comment.application.dto.CommentResponse;
import issac.issac_server.post.application.PostFinder;
import issac.issac_server.post.domain.Post;
import issac.issac_server.user.application.UserFinder;
import issac.issac_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentAppender commentAppender;
    private final UserFinder userFinder;
    private final PostFinder postFinder;

    @Transactional
    public CommentResponse save(Long userId, Long postId, CommentCreateRequest request) {
        Post post = postFinder.find(postId);
        post.validatePostIsActive();
        User user = userFinder.find(userId);
        return commentAppender.append(user, post.getId(), request);
    }
}
