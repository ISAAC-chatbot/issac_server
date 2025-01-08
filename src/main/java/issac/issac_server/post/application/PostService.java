package issac.issac_server.post.application;

import issac.issac_server.post.application.dto.*;
import issac.issac_server.post.domain.Post;
import issac.issac_server.post.domain.PostPhoto;
import issac.issac_server.reaction.application.ReactionReader;
import issac.issac_server.reaction.domain.ReactionType;
import issac.issac_server.reaction.domain.TargetType;
import issac.issac_server.user.application.UserFinder;
import issac.issac_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostAppender postAppender;
    private final PostPhotoAppender postPhotoAppender;
    private final PostFinder postFinder;
    private final UserFinder userFinder;
    private final PostPhotoFinder postPhotoFinder;
    private final PostUpdater postUpdater;
    private final PostPhotoUpdater postPhotoUpdater;
    private final ReactionReader reactionReader;

    @Transactional
    public PostResponse save(Long userId, PostCreateRequest request) {

        User user = userFinder.find(userId);

        Post post = postAppender.append(user, request);
        List<PostPhoto> postPhotos = postPhotoAppender.append(post.getId(), request.getPhotoUrls());

        return PostResponse.from(post, postPhotos, user);
    }

    public PostResponse find(Long userId, Long postId) {

        Post post = postFinder.find(postId);

        List<PostPhoto> postPhotos = postPhotoFinder.find(post.getId());
        List<ReactionStatusResponse> reactionStatusResponses = reactionReader.getReactionStatusResponses(userId, TargetType.POST, post.getId().toString());

        return PostResponse.from(post, postPhotos, reactionStatusResponses);
    }

    @Transactional
    public PostResponse update(Long userId, Long postId, PostUpdateRequest request) {

        Post post = postFinder.find(postId);

        postUpdater.update(userId, post, request);
        List<PostPhoto> postPhotos = postPhotoUpdater.update(post.getId(), request.getPhotoUrls());
        List<ReactionStatusResponse> reactionStatusResponses = reactionReader.getReactionStatusResponses(userId, TargetType.POST, post.getId().toString());

        return PostResponse.from(post, postPhotos, reactionStatusResponses);
    }

    public Page<PostPreviewResponse> findPosts(PostSearchCondition condition, Pageable pageable) {
        return postFinder.findPosts(condition, pageable).map(PostPreviewResponse::from);
    }

    public Page<PostPreviewResponse> findMyPosts(Long userId, Pageable pageable) {
        return postFinder.findMyPosts(userId, pageable);
    }

    public Page<PostPreviewResponse> findPostsByReaction(Long userId, ReactionType reactionType, Pageable pageable) {
        return postFinder.findPostsByReaction(userId, reactionType, pageable);
    }
}
