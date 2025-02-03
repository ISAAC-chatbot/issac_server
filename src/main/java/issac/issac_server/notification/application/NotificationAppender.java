package issac.issac_server.notification.application;

import issac.issac_server.batch.application.dto.BookmarkQueueRequest;
import issac.issac_server.batch.application.dto.KeywordQueueRequest;
import issac.issac_server.notification.application.dto.NotificationRequest;
import issac.issac_server.notification.domain.Notification;
import issac.issac_server.notification.domain.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationAppender {

    private final NotificationRepository notificationRepository;

    public void appendAll(KeywordQueueRequest message) {
        List<Notification> notifications = message.getUserIds().stream().map(userId -> Notification.of(userId, message.getRequest())).toList();
        notificationRepository.saveAll(notifications);
    }

    public void append(BookmarkQueueRequest message) {
        notificationRepository.save(Notification.of(message.getUserId(), message.getRequest()));
    }

    public void appendAll(List<Long> userIds, NotificationRequest request) {
        List<Notification> notifications = userIds.stream().map(userId -> Notification.of(userId, request)).toList();
        notificationRepository.saveAll(notifications);
    }
}
