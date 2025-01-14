package issac.issac_server.batch.application.dto;

import issac.issac_server.notification.application.dto.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkQueueRequest {
    private Long userId;
    private NotificationRequest request;
}
