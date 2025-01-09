package issac.issac_server.auth.application.oauth.kakao;

import issac.issac_server.auth.application.dto.OAuthInfo;
import issac.issac_server.auth.application.oauth.OAuthClient;
import issac.issac_server.auth.domain.OAuthProviderType;
import issac.issac_server.user.domain.OAuthInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoOAuthClient implements OAuthClient {

    private final KakaoApiClient kakaoApiClient;

    @Value("${oauth.kakao.admin-key}")
    private String adminKey;

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
    public void revoke(OAuthInformation oauthInformation) {
        kakaoApiClient.revoke("KakaoAK " + adminKey, oauthInformation.getOauthId(), "user_id");
    }

}
