package issac.issac_server.auth.application.oauth.apple;


import issac.issac_server.auth.application.dto.OAuthInfo;
import issac.issac_server.auth.application.oauth.OAuthClient;
import issac.issac_server.auth.domain.OAuthProviderType;
import issac.issac_server.auth.infrastructure.JwtDecoder;
import issac.issac_server.auth.infrastructure.JwtGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppleOAuthClient implements OAuthClient {


    private final JwtDecoder jwtDecoder;
    private final JwtGenerator jwtGenerator;
    private final AppleApiClient appleApiClient;

    @Value("${oauth.apple.team-id}")
    private String teamId;

    @Value("${oauth.apple.client-id}")
    private String clientId;

    @Value("${oauth.apple.key-id}")
    private String keyId;

    @Value("${oauth.apple.private-key}")
    private String privateKey;


    @Override
    public OAuthProviderType oauthProvider() {
        return OAuthProviderType.APPLE;
    }

    @Override
    public OAuthInfo getOAuthInfo(String token) {
        AppleInfoResponse appleInfoResponse = AppleInfoResponse.from(jwtDecoder.decodeIdToken(token));
        return appleInfoResponse.toOauthInfo();
    }

    @Override
    public void revoke(String code) {
        String clientSecret = jwtGenerator.generateAppleClientSecret(teamId, clientId, keyId, privateKey);
        AppleTokenResponse userInfo = appleApiClient.findUserToken(AppleTokenRequest.from(clientId, clientSecret, code));
        appleApiClient.revoke(AppleRevokeRequest.from(clientId, clientSecret, userInfo.getRefreshToken()));
    }

}
