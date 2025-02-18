package issac.issac_server.auth.application.oauth.apple;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppleRevokeRequest {

    private String client_id;
    private String client_secret;
    private String token;
    private String token_type_hint;

    public static AppleRevokeRequest from(String client_id, String client_secret, String token) {
        return new AppleRevokeRequest(client_id, client_secret, token, "refresh_token");
    }
}
