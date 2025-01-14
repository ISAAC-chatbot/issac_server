package issac.issac_server.notification.application;

import issac.issac_server.batch.application.dto.RabbitMQResponse;
import issac.issac_server.notification.domain.Notification;
import issac.issac_server.notification.domain.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationAppender {

    private final NotificationRepository notificationRepository;

    public void appendAll(RabbitMQResponse message) {
        List<Notification> notifications = message.getUserIds().stream().map(userId -> Notification.from(userId, message.getRequest())).toList();
        notificationRepository.saveAll(notifications);
    }
}
