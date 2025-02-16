package issac.issac_server.notification.application.dto;

import issac.issac_server.notification.domain.NotificationType;
import issac.issac_server.reaction.domain.TargetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationUpdateRequest {

    @NotNull
    private NotificationType notificationType;

    @NotNull
    private TargetType entityType;

    @NotBlank
    private String entityId;

}
