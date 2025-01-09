package issac.issac_server.auth.application;

import issac.issac_server.auth.domain.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefreshTokenRemover {

    private final RefreshTokenRepository refreshTokenRepository;

    public void remove(Long userId) {
        refreshTokenRepository.deleteById(userId);
    }
}
