package issac.issac_server.post.domain.repository;

import issac.issac_server.common.domain.EntityStatus;
import issac.issac_server.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

    @Query(value = """
        SELECT *
        FROM post p
        WHERE p.entity_status = :entityStatus
        AND (
            MATCH(p.title) AGAINST(:keyword IN BOOLEAN MODE)
            OR MATCH(p.content) AGAINST(:keyword IN BOOLEAN MODE)
        )
        """,
            countQuery = """
        SELECT COUNT(*)
        FROM post p
        WHERE p.entity_status = :entityStatus
        AND (
            MATCH(p.title) AGAINST(:keyword IN BOOLEAN MODE)
            OR MATCH(p.content) AGAINST(:keyword IN BOOLEAN MODE)
        )
        """,
            nativeQuery = true)
    Page<Post> searchByFullTextAndEntityStatus(
            @Param("keyword") String keyword,
            @Param("entityStatus") EntityStatus entityStatus,
            Pageable pageable
    );


}
