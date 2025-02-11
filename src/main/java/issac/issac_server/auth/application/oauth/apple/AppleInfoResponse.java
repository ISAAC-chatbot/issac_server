package issac.issac_server.auth.application.oauth.apple;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import issac.issac_server.auth.application.dto.OAuthInfo;
import issac.issac_server.auth.domain.OAuthProviderType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class AppleInfoResponse {

    private String iss;
    private String aud;
    private String exp;
    private String iat;
    private String sub;
    private String email;
    private String emailVerified;
    private String isPrivateEmail;

    public OAuthInfo toOauthInfo() {
        return new OAuthInfo(sub, email, OAuthProviderType.APPLE);
    }

    public static AppleInfoResponse from(Map<String, Object> payload) {

        String iss = (String) payload.get("iss");
        String aud = (String) payload.get("aud");
        String exp = String.valueOf(payload.get("exp"));
        String iat = String.valueOf(payload.get("iat"));
        String sub = (String) payload.get("sub");
        String email = (String) payload.get("email");
        String emailVerified = String.valueOf(payload.get("email_verified"));
        String isPrivateEmail = String.valueOf(payload.get("is_private_email"));

        return new AppleInfoResponse(iss, aud, exp, iat, sub, email, emailVerified, isPrivateEmail);
    }


}
