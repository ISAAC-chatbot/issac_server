package issac.issac_server.comment.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Slice<Comment> findAllByPostIdAndParentIsNull(Long postId, Pageable pageable);

    Long countAllByPostId(Long postId);
}
