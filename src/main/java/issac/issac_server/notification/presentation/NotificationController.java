package issac.issac_server.notification.presentation;

import issac.issac_server.auth.config.auth.Auth;
import issac.issac_server.notification.application.NotificationService;
import issac.issac_server.notification.application.dto.*;
import jakarta.validation.Valid;
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
    public ResponseEntity<Void> markAsReadById(@Auth Long userId, @PathVariable Long notificationId) {
        notificationService.markAsReadById(userId, notificationId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/read")
    public ResponseEntity<Void> markAsRead(@Auth Long userId, @RequestBody @Valid NotificationUpdateRequest request) {
        notificationService.markAsRead(userId, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendNotification(@RequestBody @Valid NotificationCreateRequest request){
        notificationService.sendNotification(request);
        return ResponseEntity.ok().build();
    }

}

