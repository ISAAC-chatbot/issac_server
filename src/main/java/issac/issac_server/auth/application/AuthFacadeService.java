package issac.issac_server.auth.application;

import issac.issac_server.auth.application.dto.LoginRequest;
import issac.issac_server.auth.application.dto.LoginResponse;
import issac.issac_server.auth.application.dto.OAuthInfo;
import issac.issac_server.auth.application.oauth.OAuthClient;
import issac.issac_server.auth.domain.OAuthProviderType;
import issac.issac_server.auth.exception.AuthErrorCode;
import issac.issac_server.auth.exception.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthFacadeService {

    private final AuthService authService;
    private final List<OAuthClient> oAuthClients;

    private OAuthClient findOAuthClient(OAuthProviderType providerType) {
        return oAuthClients.stream()
                .filter(handler -> handler.oauthProvider() == providerType)
                .findFirst()
                .orElseThrow(() -> new AuthException(AuthErrorCode.UNSUPPORTED_OAUTH_TYPE));
    }

    @Transactional
    public LoginResponse login(LoginRequest request) {
        OAuthClient oAuthClient = findOAuthClient(request.getProvider());
        OAuthInfo oAuthInfo = oAuthClient.getOAuthInfo(request.getOauthToken());
        return authService.login(oAuthInfo);
    }

    public LoginResponse guestLogin() {
        return authService.guestLogin();
    }
}
