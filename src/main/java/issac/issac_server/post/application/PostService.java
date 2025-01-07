package issac.issac_server.post.application;

import issac.issac_server.post.application.dto.PostCreateRequest;
import issac.issac_server.post.application.dto.PostResponse;
import issac.issac_server.post.application.dto.ReactionStatusResponse;
import issac.issac_server.post.domain.Post;
import issac.issac_server.post.domain.PostPhoto;
import issac.issac_server.reaction.application.ReactionReader;
import issac.issac_server.reaction.domain.TargetType;
import issac.issac_server.user.application.UserFinder;
import issac.issac_server.user.domain.User;
import lombok.RequiredArgsConstructor;
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

        Post post = postAppender.append(userId, request);
        List<PostPhoto> postPhotos = postPhotoAppender.append(post.getId(), request.getPhotoUrls());

        return PostResponse.from(post, postPhotos, user);
    }

    public PostResponse find(Long userId, Long postId) {

        Post post = postFinder.find(postId);
        User user = userFinder.find(userId);

        List<PostPhoto> postPhotos = postPhotoFinder.find(post.getId());
        List<ReactionStatusResponse> reactionStatusResponses = reactionReader.getReactionStatusResponses(userId, TargetType.POST, post.getId().toString());

        return PostResponse.from(post, postPhotos, user, reactionStatusResponses);
    }

    @Transactional
    public PostResponse update(Long userId, Long postId, PostUpdateRequest request) {

        Post post = postFinder.find(postId);
        User user = userFinder.find(userId);

        postUpdater.update(post, request);
        List<PostPhoto> postPhotos = postPhotoUpdater.update(post.getId(), request.getPhotoUrls());
        List<ReactionStatusResponse> reactionStatusResponses = reactionReader.getReactionStatusResponses(userId, TargetType.POST, post.getId().toString());

        return PostResponse.from(post, postPhotos, user, reactionStatusResponses);
    }
}
