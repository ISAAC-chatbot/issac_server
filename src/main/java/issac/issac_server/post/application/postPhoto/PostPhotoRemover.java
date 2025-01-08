package issac.issac_server.post.application.postPhoto;

import issac.issac_server.file.application.S3Remover;
import issac.issac_server.post.domain.PostPhoto;
import issac.issac_server.post.domain.repository.PostPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostPhotoRemover {

    private final PostPhotoRepository postPhotoRepository;
    private final PostPhotoFinder postPhotoFinder;
    private final S3Remover s3Remover;

    public void deleteAllPhotosByPostId(Long postId) {

        List<String> photoUrls = postPhotoFinder.find(postId).stream()
                .map(PostPhoto::getPhotoUrl)
                .collect(Collectors.toList());

        deletePhotosByUrls(postId, photoUrls);
    }

    public void deletePhotosByUrls(Long postId, List<String> urlsToDelete) {
        s3Remover.deleteObjects(urlsToDelete);
        postPhotoRepository.deleteAllByPostId(postId);
    }
}
