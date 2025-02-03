package issac.issac_server.notification.application.dto;

import issac.issac_server.notification.domain.NotificationType;
import issac.issac_server.reaction.domain.TargetType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationSearchCondition {
    private NotificationType notificationType;
    private TargetType entityType;
    private Boolean read;
}
