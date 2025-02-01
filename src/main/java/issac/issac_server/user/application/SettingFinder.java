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
public class SettingFinder {

    private final DeviceTokenFinder deviceTokenFinder;
    private final DeviceTokenAppender deviceTokenAppender;
    private final UserFinder userFinder;

    public SettingResponse find(Long userId, SettingType settingItem) {

        return switch (settingItem) {
            case SettingType.MARKETING -> {
                User user = userFinder.find(userId);
                yield SettingResponse.from(user);
            }
            case SettingType.NOTIFICATION -> {
                DeviceToken deviceToken = deviceTokenFinder.find(userId).orElseGet(() -> deviceTokenAppender.append(userId, ""));
                yield SettingResponse.from(deviceToken);
            }
        };
    }
}
