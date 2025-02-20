package issac.issac_server.device.application;

import issac.issac_server.device.domain.DeviceTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeviceTokenRemover {
    private final DeviceTokenRepository deviceTokenRepository;

    public void remove(Long userId){
        deviceTokenRepository.deleteByUserId(userId);
    }
}
