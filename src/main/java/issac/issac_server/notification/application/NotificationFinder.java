package issac.issac_server.notification.application;

import issac.issac_server.notification.domain.Notification;
import issac.issac_server.notification.domain.NotificationRepository;
import issac.issac_server.notification.exception.NotificationErrorCode;
import issac.issac_server.notification.exception.NotificationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationFinder {

    private final NotificationRepository notificationRepository;

    public Page<Notification> findNotifications(Long userId, Pageable pageable) {
        Pageable sortedByIdDesc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "id"));
        return notificationRepository.findByUserId(userId, sortedByIdDesc);
    }

    public Notification find(Long notificationId) {
        return notificationRepository.findById(notificationId).orElseThrow(() -> new NotificationException(NotificationErrorCode.NOT_FOUND));
    }
}
