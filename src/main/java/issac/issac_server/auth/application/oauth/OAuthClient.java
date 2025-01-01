package issac.issac_server.auth.application.oauth;

import issac.issac_server.auth.application.dto.OAuthInfo;
import issac.issac_server.auth.domain.OAuthProviderType;

public interface OAuthClient {

    OAuthProviderType oauthProvider();

    OAuthInfo getOAuthInfo(String token);

}
