package issac.issac_server.auth.application.oauth.kakao;

import issac.issac_server.auth.application.dto.OAuthInfo;
import issac.issac_server.auth.application.dto.OAuthTokenRequest;
import issac.issac_server.auth.application.oauth.OAuthClient;
import issac.issac_server.auth.domain.OAuthProviderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoOAuthClient implements OAuthClient {

    private final KakaoApiClient kakaoApiClient;

    @Override
    public OAuthProviderType oauthProvider() {
        return OAuthProviderType.KAKAO;
    }

    @Override
    public OAuthInfo getOAuthInfo(String token) {
        KakaoInfoResponse userInfo = kakaoApiClient.findUserInfo("Bearer " + token);
        return userInfo.toOauthInfo();
    }

    @Override
    public void revoke(OAuthTokenRequest request) {
        kakaoApiClient.revoke("Bearer " + request.getOauthToken());
    }

}
