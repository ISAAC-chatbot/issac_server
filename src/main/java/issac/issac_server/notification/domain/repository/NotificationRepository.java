package issac.issac_server.notification.domain.repository;

import issac.issac_server.notification.domain.Notification;
import issac.issac_server.notification.domain.NotificationType;
import issac.issac_server.reaction.domain.TargetType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long>, NotificationRepositoryCustom {

    Page<Notification> findByUserId(Long userId, Pageable pageable);

    Optional<Notification> findByUserIdAndNotificationTypeAndEntityTypeAndEntityId(Long userId, NotificationType notificationType, TargetType entityType, String entityId);

    void deleteAllByUserId(Long userId);
}
