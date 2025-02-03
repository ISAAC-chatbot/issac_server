package issac.issac_server.notification.domain.repository;

import issac.issac_server.notification.domain.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long>, NotificationRepositoryCustom {

    Page<Notification> findByUserId(Long userId, Pageable pageable);
}
