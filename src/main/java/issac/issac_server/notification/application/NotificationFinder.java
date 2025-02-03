package issac.issac_server.notification.application;

import issac.issac_server.notification.application.dto.NotificationSearchCondition;
import issac.issac_server.notification.domain.Notification;
import issac.issac_server.notification.domain.repository.NotificationRepository;
import issac.issac_server.notification.exception.NotificationErrorCode;
import issac.issac_server.notification.exception.NotificationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationFinder {

    private final NotificationRepository notificationRepository;

    public Page<Notification> findNotifications(Long userId, NotificationSearchCondition condition, Pageable pageable) {
        return notificationRepository.findNotifications(userId, condition , pageable);
    }

    public Notification find(Long notificationId) {
        return notificationRepository.findById(notificationId).orElseThrow(() -> new NotificationException(NotificationErrorCode.NOT_FOUND));
    }
}
