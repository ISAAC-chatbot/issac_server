package issac.issac_server.auth.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppleAuthorizationCodeRequest {
    private String code;
    private String id_token;
    private String state;
}
