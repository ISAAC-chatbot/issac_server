package issac.issac_server.auth.application.dto;

import issac.issac_server.auth.domain.OAuthProviderType;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequest {

    @NotNull
    private OAuthProviderType provider;

    @NotNull
    private String oauthToken;

    @Builder
    public LoginRequest(OAuthProviderType provider, String oauthToken) {
        this.provider = provider;
        this.oauthToken = oauthToken;
    }
}
