package issac.issac_server.user.application.dto;

import issac.issac_server.device.domain.DeviceToken;
import issac.issac_server.user.domain.SettingType;
import issac.issac_server.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SettingResponse {
    private final SettingType type;
    private final Boolean active;

    public static SettingResponse from(DeviceToken token) {
        return new SettingResponse(SettingType.NOTIFICATION, token.isNotificationConsent());
    }

    public static SettingResponse from(User user) {
        return new SettingResponse(SettingType.MARKETING, user.isMarketingConsent());
    }
}
