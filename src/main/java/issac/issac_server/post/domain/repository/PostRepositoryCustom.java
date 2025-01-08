package issac.issac_server.post.domain.repository;

import issac.issac_server.post.application.dto.PostSearchCondition;
import issac.issac_server.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    Page<Post> findPosts(PostSearchCondition condition, Pageable pageable);
}
