package issac.issac_server.auth.application;

import issac.issac_server.auth.application.dto.*;
import issac.issac_server.auth.application.oauth.OAuthClient;
import issac.issac_server.auth.domain.OAuthProviderType;
import issac.issac_server.auth.exception.AuthErrorCode;
import issac.issac_server.auth.exception.AuthException;
import issac.issac_server.user.application.UserFinder;
import issac.issac_server.user.application.event.UserRevokeEvent;
import issac.issac_server.user.domain.User;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthFacadeService {

    private final AuthService authService;
    private final List<OAuthClient> oAuthClients;
    private final UserFinder userFinder;

    private final ApplicationEventPublisher publisher;

    private OAuthClient findOAuthClient(OAuthProviderType providerType) {
        return oAuthClients.stream()
                .filter(handler -> handler.oauthProvider() == providerType)
                .findFirst()
                .orElseThrow(() -> new AuthException(AuthErrorCode.UNSUPPORTED_OAUTH_TYPE));
    }

    @Transactional
    public LoginResponse login(OAuthTokenRequest request) {
        OAuthClient oAuthClient = findOAuthClient(request.getProvider());
        OAuthInfo oAuthInfo = oAuthClient.getOAuthInfo(request.getOauthToken());
        return authService.login(oAuthInfo);
    }

    public LoginResponse guestLogin() {
        return authService.guestLogin();
    }

    @Transactional
    public LoginResponse refresh(RefreshTokenRequest request) {
        return authService.refresh(request);
    }

    public EmailResponse sendEmailVerification(EmailRequest request) throws MessagingException {
        return authService.sendEmailVerification(request);
    }

    @Transactional
    public void revoke(Long userId, UserRevokeRequest request) {
        User user = userFinder.find(userId);
        OAuthClient oAuthClient = findOAuthClient(user.getOauthInformation().getOauthProvider());
        oAuthClient.revoke(request.getToken());
        authService.revoke(user);
        publisher.publishEvent(new UserRevokeEvent(user.getId(), request.getReasonType(), request.getReasonDescription()));
    }
}
