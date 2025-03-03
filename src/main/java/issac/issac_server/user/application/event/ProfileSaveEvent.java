package issac.issac_server.user.application.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileSaveEvent {
    private Long userId;
}
