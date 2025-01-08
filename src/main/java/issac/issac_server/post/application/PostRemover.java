package issac.issac_server.post.application;

import issac.issac_server.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostRemover {

    public void remove(Long userId, Post post){
        post.validateAuthor(userId);
        post.delete();
    }
}
