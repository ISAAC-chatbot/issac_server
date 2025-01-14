package issac.issac_server.device.application;

import issac.issac_server.device.domain.DeviceToken;
import issac.issac_server.device.domain.DeviceTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DeviceTokenFinder {

    private final DeviceTokenRepository deviceTokenRepository;

    public Optional<DeviceToken> find(Long userId) {
        return deviceTokenRepository.findById(userId);
    }
}
