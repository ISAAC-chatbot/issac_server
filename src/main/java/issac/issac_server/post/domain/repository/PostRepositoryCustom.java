package issac.issac_server.post.domain.repository;

import issac.issac_server.post.application.dto.request.PostSearchCondition;
import issac.issac_server.post.domain.Post;
import issac.issac_server.reaction.domain.ReactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    Page<Post> findPosts(PostSearchCondition condition, Pageable pageable);

    Page<Post> findPostsWithMyReaction(Long userId, ReactionType reactionType, Pageable pageable);

    Page<Post> findPostsWithMyComment(Long userId, Pageable pageable);
}
