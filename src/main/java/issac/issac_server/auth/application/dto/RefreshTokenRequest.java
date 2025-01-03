package issac.issac_server.auth.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshTokenRequest {

    @NotNull
    private String token;

    public RefreshTokenRequest(String token) {
        this.token = token;
    }
}
