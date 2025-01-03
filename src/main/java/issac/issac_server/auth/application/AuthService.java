package issac.issac_server.auth.application;

import issac.issac_server.auth.application.dto.LoginResponse;
import issac.issac_server.auth.application.dto.OAuthInfo;
import issac.issac_server.auth.application.dto.RefreshTokenRequest;
import issac.issac_server.auth.domain.RefreshToken;
import issac.issac_server.auth.domain.RefreshTokenRepository;
import issac.issac_server.auth.infrastructure.JwtTokenProvider;
import issac.issac_server.user.application.UserFinder;
import issac.issac_server.user.domain.Role;
import issac.issac_server.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserFinder userFinder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenFinder refreshTokenFinder;

    public LoginResponse login(OAuthInfo oAuthInfo) {
        User user = userFinder.find(oAuthInfo);
        return generateToken(user);
    }

    @Transactional(readOnly = true)
    public LoginResponse guestLogin() {
        String accessToken = jwtTokenProvider.generateGuestAccessToken();
        return new LoginResponse(accessToken, Role.GUEST);
    }

    public LoginResponse refresh(RefreshTokenRequest request) {
        jwtTokenProvider.validateRefreshToken(request.getToken());
        RefreshToken refreshToken = refreshTokenFinder.find(request.getToken());
        User user = userFinder.find(refreshToken.getUserId());
        return generateToken(user);
    }

    private LoginResponse generateToken(User user) {
        String accessToken = jwtTokenProvider.generateAccessToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);
        return new LoginResponse(accessToken, refreshToken, user.getRole());
    }
}
