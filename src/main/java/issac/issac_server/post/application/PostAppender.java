package issac.issac_server.post.application;

import issac.issac_server.post.application.dto.request.PostCreateRequest;
import issac.issac_server.post.domain.Post;
import issac.issac_server.post.domain.repository.PostRepository;
import issac.issac_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostAppender {

    private final PostRepository postRepository;

    public Post append(User user, PostCreateRequest request) {
        return postRepository.save(Post.of(user, request));
    }
}
