package issac.issac_server.post.application;

import issac.issac_server.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostUpdater {

    public void update(Post post, PostUpdateRequest request) {
        post.update(request);
    }
}
