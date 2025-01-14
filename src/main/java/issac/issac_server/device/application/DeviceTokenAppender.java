package issac.issac_server.device.application;

import issac.issac_server.device.domain.DeviceToken;
import issac.issac_server.device.domain.DeviceTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeviceTokenAppender {

    private final DeviceTokenRepository deviceTokenRepository;

    public DeviceToken append(Long userId, String token) {
        return deviceTokenRepository.save(new DeviceToken(userId, token));
    }
}
