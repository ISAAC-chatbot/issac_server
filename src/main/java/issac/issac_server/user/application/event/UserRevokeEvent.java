package issac.issac_server.user.application.event;

import issac.issac_server.user.domain.RevokeReasonType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRevokeEvent {
    private Long userId;
    private RevokeReasonType reasonType;
    private String reasonDescription;
}
