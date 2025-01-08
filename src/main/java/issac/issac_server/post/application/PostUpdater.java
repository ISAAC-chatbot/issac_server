package issac.issac_server.post.application;

import issac.issac_server.post.domain.Post;
import issac.issac_server.post.exception.PostErrorCode;
import issac.issac_server.post.exception.PostException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostUpdater {

    public void update(Long userId, Post post, PostUpdateRequest request) {
        validateAuthor(userId,post);
        post.update(request);
    }
    private void validateAuthor(Long userId, Post post) {
        if (!post.getAuthor().getId().equals(userId)) {
            throw new PostException(PostErrorCode.USER_IS_NOT_AUTHOR);
        }
    }
}
