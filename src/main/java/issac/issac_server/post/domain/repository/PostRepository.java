package issac.issac_server.post.domain.repository;

import issac.issac_server.common.domain.EntityStatus;
import issac.issac_server.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

    Page<Post> findAllByAuthorIdAndEntityStatus(Long userId, EntityStatus entityStatus, Pageable pageable);
}
