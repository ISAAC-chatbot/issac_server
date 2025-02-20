package issac.issac_server.notice.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Optional<Bookmark> findByUserIdAndSource(Long userId, NoticeSource source);

    boolean existsByUserIdAndSource(Long userId, NoticeSource source);

    void deleteAllByUserId(Long userId);

    void deleteAllByUserIdAndSourceIn(Long userId, List<NoticeSource> sources);

    List<Bookmark> findAllByUserId(Long userId);

    // spring batch -> BookmarkJob
    Page<Bookmark> findBySourceAndNotificationConsent(NoticeSource source, boolean notificationConsent, Pageable pageable);

    void deleteByUserIdAndSource(Long userId, NoticeSource source);

}
