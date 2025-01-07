package issac.issac_server.post.domain.repository;

import issac.issac_server.post.domain.PostPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostPhotoRepository extends JpaRepository<PostPhoto, Long> {

    List<PostPhoto> findAllByPostId(Long postId);

    void deleteAllByPostId(Long postId);
}
