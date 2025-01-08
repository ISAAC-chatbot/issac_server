package issac.issac_server.post.application;

import issac.issac_server.post.application.dto.request.PostUpdateRequest;
import issac.issac_server.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostUpdater {

    private final PostFinder postFinder;

    public void update(Long userId, Post post, PostUpdateRequest request) {
        post.validateAuthor(userId);
        post.update(request);
    }

    public void updateLikeCount(Long postId, Long count) {
        Post post = postFinder.find(postId);
        post.updateLikeCount(count);
    }
}
