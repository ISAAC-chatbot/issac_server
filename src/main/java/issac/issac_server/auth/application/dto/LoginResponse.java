package issac.issac_server.auth.application.dto;

import issac.issac_server.user.domain.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponse {

    private String accessToken;
    private String refreshToken;
    private Role role;

    @Builder
    public LoginResponse(String accessToken, String refreshToken, Role role) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.role = role;
    }

    public LoginResponse(String accessToken, Role role) {
        this.accessToken = accessToken;
        this.role = role;
    }

}
