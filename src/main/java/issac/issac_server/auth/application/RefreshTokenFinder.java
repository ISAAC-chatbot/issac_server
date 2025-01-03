package issac.issac_server.auth.application;

import issac.issac_server.auth.domain.RefreshToken;
import issac.issac_server.auth.domain.RefreshTokenRepository;
import issac.issac_server.auth.exception.AuthErrorCode;
import issac.issac_server.auth.exception.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefreshTokenFinder {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken find(String token) {
        return  refreshTokenRepository.findByToken(token).orElseThrow(() -> new AuthException(AuthErrorCode.REFRESH_TOKEN_NOT_FOUND));
    }
}
