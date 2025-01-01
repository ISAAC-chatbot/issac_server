package issac.issac_server.auth.application.dto;

import issac.issac_server.auth.domain.OAuthProviderType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OAuthInfo {
    private String id;
    private String email;
    private OAuthProviderType provider;
}
