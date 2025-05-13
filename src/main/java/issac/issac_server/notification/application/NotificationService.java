package issac.issac_server.notification.application;

import issac.issac_server.notification.application.dto.NotificationCreateRequest;
import issac.issac_server.notification.application.dto.NotificationResponse;
import issac.issac_server.notification.application.dto.NotificationSearchCondition;
import issac.issac_server.notification.application.dto.NotificationUpdateRequest;
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
    private final FCMSender fcmSender;

    public Page<NotificationResponse> findNotifications(Long userId, NotificationSearchCondition condition, Pageable pageable) {
        return notificationFinder.findNotifications(userId, condition, pageable).map(NotificationResponse::from);
    }

    @Transactional
    public void markAsReadById(Long userId, Long notificationId) {
        Notification notification = notificationFinder.find(notificationId);
        notification.markAsRead(userId);
    }

    @Transactional
    public void markAsRead(Long userId, NotificationUpdateRequest request) {
        Notification notification = notificationFinder.find(userId, request.getNotificationType(), request.getEntityType(), request.getEntityId());
        notification.markAsRead(userId);
    }

    public void sendNotification(NotificationCreateRequest request) {
        fcmSender.sendBulk(request);
    }
}
