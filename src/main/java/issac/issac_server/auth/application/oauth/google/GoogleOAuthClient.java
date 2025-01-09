package issac.issac_server.auth.application.oauth.google;

import issac.issac_server.auth.application.dto.OAuthInfo;
import issac.issac_server.auth.application.oauth.OAuthClient;
import issac.issac_server.auth.domain.OAuthProviderType;
import issac.issac_server.user.domain.OAuthInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GoogleOAuthClient implements OAuthClient {

    private final GoogleApiClient googleApiClient;

    @Override
    public OAuthProviderType oauthProvider() {
        return OAuthProviderType.GOOGLE;
    }

    @Override
    public OAuthInfo getOAuthInfo(String token) {
        GoogleInfoResponse userInfo = googleApiClient.findUserInfo("Bearer " + token);
        return userInfo.toOauthInfo();
    }

    @Override
    public void revoke(OAuthInformation oauthInformation) {

    }

}
