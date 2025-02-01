package issac.issac_server.user.application;

import issac.issac_server.device.application.DeviceTokenAppender;
import issac.issac_server.device.application.DeviceTokenFinder;
import issac.issac_server.device.domain.DeviceToken;
import issac.issac_server.user.application.dto.SettingResponse;
import issac.issac_server.user.domain.SettingType;
import issac.issac_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SettingUpdater {

    private final UserFinder userFinder;
    private final DeviceTokenFinder deviceTokenFinder;
    private final DeviceTokenAppender deviceTokenAppender;

    public SettingResponse toggle(Long userId, SettingType settingItem) {
        return switch (settingItem) {
            case SettingType.MARKETING -> {
                User user = userFinder.find(userId);
                user.toggleMarketingConsent();
                yield SettingResponse.from(user);
            }
            case SettingType.NOTIFICATION -> {
                DeviceToken deviceToken = deviceTokenFinder.find(userId).orElseGet(() -> deviceTokenAppender.append(userId, ""));
                deviceToken.toggleNotificationConsent();
                yield SettingResponse.from(deviceToken);
            }
        };
    }
}
