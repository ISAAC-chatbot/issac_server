package issac.issac_server.auth.application;

import issac.issac_server.auth.application.dto.LoginResponse;
import issac.issac_server.auth.application.dto.OAuthInfo;
import issac.issac_server.auth.infrastructure.JwtTokenProvider;
import issac.issac_server.user.application.UserFinder;
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

    public LoginResponse login(OAuthInfo oAuthInfo) {
        User user = userFinder.find(oAuthInfo);
        String accessToken = jwtTokenProvider.generateAccessToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);
        return new LoginResponse(accessToken, refreshToken, user.getRole());
    }
}
