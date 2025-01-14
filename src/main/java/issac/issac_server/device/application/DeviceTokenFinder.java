package issac.issac_server.device.application;

import issac.issac_server.device.domain.DeviceToken;
import issac.issac_server.device.domain.DeviceTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DeviceTokenFinder {

    private final DeviceTokenRepository deviceTokenRepository;

    public Optional<DeviceToken> find(Long userId) {
        return deviceTokenRepository.findById(userId);
    }

    public Set<String> findDistinctTokens(List<Long> userIds){
        return deviceTokenRepository.findDistinctTokensByUserIdIn(userIds);
    }
}
