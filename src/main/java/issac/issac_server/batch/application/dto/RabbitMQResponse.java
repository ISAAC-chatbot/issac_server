package issac.issac_server.batch.application.dto;

import issac.issac_server.notification.application.dto.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RabbitMQResponse {
    private List<Long> userIds;
    private NotificationRequest request;
}
