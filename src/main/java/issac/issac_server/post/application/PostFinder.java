package issac.issac_server.post.application;

import issac.issac_server.common.domain.EntityStatus;
import issac.issac_server.post.application.dto.PostSearchCondition;
import issac.issac_server.post.domain.Post;
import issac.issac_server.post.domain.repository.PostRepository;
import issac.issac_server.post.exception.PostErrorCode;
import issac.issac_server.post.exception.PostException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostFinder {

    private final PostRepository postRepository;

    public Post find(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostException(PostErrorCode.NOT_FOUND));
    }

    public Page<Post> findPosts(PostSearchCondition condition, Pageable pageable) {
        return postRepository.searchByFullTextAndEntityStatus(condition.getKeyword(), EntityStatus.ACTIVE, pageable);
    }
}
