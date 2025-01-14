package issac.issac_server.device.application;

import issac.issac_server.device.application.dto.DeviceTokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeviceTokenService {

    private final DeviceTokenUpdater deviceTokenUpdater;

    @Transactional
    public void updateToken(Long userId, DeviceTokenRequest request) {
        deviceTokenUpdater.update(userId, request);
    }
}
