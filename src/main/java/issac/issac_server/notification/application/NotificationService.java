package issac.issac_server.notification.application;

import issac.issac_server.notification.application.dto.NotificationResponse;
import issac.issac_server.notification.domain.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationFinder notificationFinder;

    public Page<NotificationResponse> findNotifications(Long userId, Pageable pageable) {
        return notificationFinder.findNotifications(userId, pageable).map(NotificationResponse::from);
    }

    @Transactional
    public void markAsRead(Long userId, Long notificationId) {
        Notification notification = notificationFinder.find(notificationId);
        notification.markAsRead(userId);
    }
}
