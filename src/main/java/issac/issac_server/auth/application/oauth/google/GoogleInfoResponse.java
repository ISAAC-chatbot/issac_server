package issac.issac_server.auth.application.oauth.google;

import issac.issac_server.auth.application.dto.OAuthInfo;
import issac.issac_server.auth.domain.OAuthProviderType;
import lombok.Data;

@Data
public class GoogleInfoResponse {

    private String id;
    private String email;
    private boolean verified_email;
    private String picture;
    private String hd;

    public OAuthInfo toOauthInfo() {
        return new OAuthInfo(id, email, OAuthProviderType.GOOGLE);
    }
}
