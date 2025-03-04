package issac.issac_server.notification.application;

import issac.issac_server.notification.domain.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationRemover {

    private final NotificationRepository notificationRepository;

    public void removeAll(Long userId){
        notificationRepository.deleteAllByUserId(userId);
    }
}
