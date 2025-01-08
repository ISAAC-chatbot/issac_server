package issac.issac_server.post.domain.repository;

import issac.issac_server.common.domain.EntityStatus;
import issac.issac_server.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM post " +
            "WHERE MATCH(title, content) AGAINST(:keyword IN BOOLEAN MODE) " +
            "AND entity_status = :entityStatus",
            countQuery = "SELECT COUNT(*) FROM post " +
                    "WHERE MATCH(title, content) AGAINST(:keyword IN BOOLEAN MODE) " +
                    "AND entity_status = :entityStatus",
            nativeQuery = true)
    Page<Post> searchByFullTextAndEntityStatus(@Param("keyword") String keyword,
                                               @Param("entityStatus") EntityStatus entityStatus,
                                               Pageable pageable);
}
