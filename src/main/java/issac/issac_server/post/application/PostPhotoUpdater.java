package issac.issac_server.post.application;

import issac.issac_server.post.domain.PostPhoto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostPhotoUpdater {

    private final PostPhotoFinder postPhotoFinder;
    private final PostPhotoRemover postPhotoRemover;
    private final PostPhotoAppender postPhotoAppender;

    public List<PostPhoto> update(Long postId, List<String> photoUrls) {

        if (photoUrls == null || photoUrls.isEmpty()) {
            postPhotoRemover.deleteAllPhotosByPostId(postId);
            return Collections.emptyList();
        }

        List<String> urlsToDelete = getUrlsToDelete(postId, photoUrls);

        postPhotoRemover.deletePhotosByUrls(postId, urlsToDelete);

        return postPhotoAppender.append(postId, photoUrls);
    }

    private List<String> getUrlsToDelete(Long postId, List<String> newPhotoUrls) {
        List<PostPhoto> existingPostPhotos = postPhotoFinder.find(postId);

        Set<String> existingPhotoUrls = existingPostPhotos.stream()
                .map(PostPhoto::getPhotoUrl)
                .collect(Collectors.toSet());

        Set<String> newPhotoUrlSet = new HashSet<>(newPhotoUrls);

        return existingPhotoUrls.stream()
                .filter(url -> !newPhotoUrlSet.contains(url))
                .collect(Collectors.toList());
    }
}
