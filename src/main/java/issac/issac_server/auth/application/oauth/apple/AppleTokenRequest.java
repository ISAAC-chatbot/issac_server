package issac.issac_server.auth.application.oauth.apple;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppleTokenRequest {

    private String client_id;
    private String client_secret;
    private String code;
    private String grant_type;

    public static AppleTokenRequest from(String client_id, String client_secret, String code){
        return new AppleTokenRequest(client_id, client_secret, code, "authorization_code");
    }
}
