package issac.issac_server.post.application;

import issac.issac_server.post.application.dto.request.PostCreateRequest;
import issac.issac_server.post.application.dto.request.PostSearchCondition;
import issac.issac_server.post.application.dto.request.PostUpdateRequest;
import issac.issac_server.post.application.dto.response.PostPreviewResponse;
import issac.issac_server.post.application.dto.response.PostResponse;
import issac.issac_server.post.application.dto.response.ReactionStatusResponse;
import issac.issac_server.post.application.event.PostCreateEvent;
import issac.issac_server.post.application.postPhoto.PostPhotoAppender;
import issac.issac_server.post.application.postPhoto.PostPhotoFinder;
import issac.issac_server.post.application.postPhoto.PostPhotoUpdater;
import issac.issac_server.post.domain.Post;
import issac.issac_server.post.domain.PostPhoto;
import issac.issac_server.reaction.application.ReactionReader;
import issac.issac_server.reaction.domain.ReactionType;
import issac.issac_server.reaction.domain.TargetType;
import issac.issac_server.user.application.UserFinder;
import issac.issac_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
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
    private final PostRemover postRemover;

    private final ApplicationEventPublisher publisher;

    @Transactional
    public PostResponse save(Long userId, PostCreateRequest request) {

        User user = userFinder.find(userId);

        Post post = postAppender.append(user, request);
        List<PostPhoto> postPhotos = postPhotoAppender.append(post.getId(), request.getPhotoUrls());

        publisher.publishEvent(new PostCreateEvent(post));
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

    public Page<PostPreviewResponse> findPostsWithMyReaction(Long userId, ReactionType reactionType, Pageable pageable) {
        return postFinder.findPostsWithMyReaction(userId, reactionType, pageable);
    }

    @Transactional
    public void remove(Long userId, Long postId) {
        Post post = postFinder.find(postId);
        postRemover.remove(userId, post);
    }

    public Page<PostPreviewResponse> findPostsWithMyComment(Long userId, Pageable pageable) {
        return postFinder.findPostsWithMyComment(userId, pageable);
    }
}
