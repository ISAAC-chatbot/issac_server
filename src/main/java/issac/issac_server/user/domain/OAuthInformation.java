package issac.issac_server.user.domain;

import issac.issac_server.auth.application.dto.OAuthInfo;
import issac.issac_server.auth.domain.OAuthProviderType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OAuthInformation {

    @Column
    private String oauthId;

    @Column
    @Enumerated(EnumType.STRING)
    private OAuthProviderType oauthProvider;

    @Column
    private String email;

    public OAuthInformation(OAuthInfo oAuthInfo) {
        this.oauthId = oAuthInfo.getId();
        this.oauthProvider = oAuthInfo.getProvider();
        this.email = oAuthInfo.getEmail();
    }
}
