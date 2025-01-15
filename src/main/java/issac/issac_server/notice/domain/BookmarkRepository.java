package issac.issac_server.notice.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Optional<Bookmark> findByUserIdAndSource(Long userId, NoticeSource source);

    void deleteAllByUserId(Long userId);

    List<Bookmark> findAllByUserId(Long userId);

    // spring batch -> BookmarkJob
    Page<Bookmark> findBySource(NoticeSource source, Pageable pageable);
}
