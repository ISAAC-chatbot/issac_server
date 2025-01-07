package issac.issac_server.post.application;

import issac.issac_server.post.domain.PostPhoto;
import issac.issac_server.post.domain.repository.PostPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostPhotoFinder {

    private final PostPhotoRepository postPhotoRepository;

    public List<PostPhoto> find(Long postId){
        return postPhotoRepository.findAllByPostId(postId);
    }
}
