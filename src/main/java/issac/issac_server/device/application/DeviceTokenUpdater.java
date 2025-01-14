package issac.issac_server.device.application;

import issac.issac_server.device.application.dto.DeviceTokenRequest;
import issac.issac_server.device.domain.DeviceToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeviceTokenUpdater {

    private final DeviceTokenFinder deviceTokenFinder;
    private final DeviceTokenAppender deviceTokenAppender;

    public void update(Long userId, DeviceTokenRequest request) {

        DeviceToken deviceToken = deviceTokenFinder.find(userId)
                .orElseGet(() -> deviceTokenAppender.append(userId, request.getToken()));

        if (!deviceToken.getToken().equals(request.getToken())) {
            deviceToken.updateToken(request.getToken());
        }
    }
}
