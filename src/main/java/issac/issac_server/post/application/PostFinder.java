package issac.issac_server.post.application;

import issac.issac_server.common.domain.EntityStatus;
import issac.issac_server.post.application.dto.response.PostPreviewResponse;
import issac.issac_server.post.application.dto.request.PostSearchCondition;
import issac.issac_server.post.domain.Post;
import issac.issac_server.post.domain.repository.PostRepository;
import issac.issac_server.post.exception.PostErrorCode;
import issac.issac_server.post.exception.PostException;
import issac.issac_server.reaction.domain.ReactionType;
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
        return postRepository.findPosts(condition, pageable);
    }

    public Page<PostPreviewResponse> findMyPosts(Long userId, Pageable pageable) {
        return postRepository.findAllByAuthorIdAndEntityStatus(userId, EntityStatus.ACTIVE, pageable).map(PostPreviewResponse::from);
    }

    public Page<PostPreviewResponse> findPostsByReaction(Long userId, ReactionType reactionType, Pageable pageable) {
        return postRepository.findPostsByReaction(userId, reactionType, pageable).map(PostPreviewResponse::from);
    }
}
