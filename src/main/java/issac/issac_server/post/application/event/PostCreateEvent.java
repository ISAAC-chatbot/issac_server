package issac.issac_server.post.application.event;

import issac.issac_server.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostCreateEvent{
    private Post post;
}
