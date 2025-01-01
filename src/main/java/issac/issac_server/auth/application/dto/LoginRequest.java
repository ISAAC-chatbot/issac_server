package issac.issac_server.auth.application.dto;

import issac.issac_server.auth.domain.OAuthProviderType;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequest {

    @NotNull
    private OAuthProviderType provider;

    @NotNull
    private String oauthToken;

}
