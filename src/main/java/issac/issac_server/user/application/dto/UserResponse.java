package issac.issac_server.user.application.dto;

import issac.issac_server.auth.domain.OAuthProviderType;
import issac.issac_server.user.domain.Role;
import issac.issac_server.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long id;

    private OAuthProviderType oauthProvider;

    private String email;

    private Role role;

    private boolean marketingConsent;

    private ProfileResponse profile;

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getOauthInformation().getOauthProvider(),
                user.getOauthInformation().getEmail(),
                user.getRole(),
                user.isMarketingConsent(),
                ProfileResponse.from(user.getProfile())
        );
    }
}
