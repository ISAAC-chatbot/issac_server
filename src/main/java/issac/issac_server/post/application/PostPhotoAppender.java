package issac.issac_server.post.application;

import issac.issac_server.post.domain.PostPhoto;
import issac.issac_server.post.domain.repository.PostPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PostPhotoAppender {

    private final PostPhotoRepository postPhotoRepository;

    public List<PostPhoto> append(Long postId, List<String> photoUrls) {

        if (photoUrls == null || photoUrls.isEmpty()) {
            return Collections.emptyList();
        }

        List<PostPhoto> postPhotos = PostPhoto.createPostPhotos(postId, photoUrls);
        return postPhotoRepository.saveAll(postPhotos);
    }
}
