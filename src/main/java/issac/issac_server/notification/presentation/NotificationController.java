package issac.issac_server.notification.presentation;

import issac.issac_server.auth.config.auth.Auth;
import issac.issac_server.notification.application.NotificationService;
import issac.issac_server.notification.application.dto.NotificationResponse;
import issac.issac_server.notification.application.dto.NotificationSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<Page<NotificationResponse>> findNotifications(
            @Auth Long userId,
            NotificationSearchCondition condition,
            Pageable pageable) {
        return ResponseEntity.ok(notificationService.findNotifications(userId, condition, pageable));
    }

    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<Void> markAsRead(@Auth Long userId, @PathVariable Long notificationId) {
        notificationService.markAsRead(userId, notificationId);
        return ResponseEntity.ok().build();
    }
}

