package issac.issac_server.notification.domain.repository;

import issac.issac_server.notification.application.dto.NotificationSearchCondition;
import issac.issac_server.notification.domain.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationRepositoryCustom {
    Page<Notification> findNotifications(Long userId, NotificationSearchCondition condition, Pageable pageable);
}
