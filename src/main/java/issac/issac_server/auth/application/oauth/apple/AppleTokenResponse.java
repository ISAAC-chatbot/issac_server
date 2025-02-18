package issac.issac_server.auth.application.oauth.apple;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(SnakeCaseStrategy.class)
public class AppleTokenResponse {

    private String accessToken;
    private String tokenType;
    private String expiresIn;
    private String refreshToken;
    private String idToken;

}
