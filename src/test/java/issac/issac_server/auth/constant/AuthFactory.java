package issac.issac_server.auth.constant;

import issac.issac_server.auth.application.dto.*;
import issac.issac_server.auth.domain.OAuthProviderType;
import issac.issac_server.user.domain.RevokeReasonType;
import issac.issac_server.user.domain.Role;
import issac.issac_server.user.domain.University;

public class AuthFactory {

    public static OAuthTokenRequest createMockOAuthTokenRequest(OAuthProviderType oAuthProviderType, String oauthToken) {
        return OAuthTokenRequest.builder()
                .provider(oAuthProviderType)
                .oauthToken(oauthToken)
                .build();
    }

    public static LoginResponse createMockLoginResponse() {
        return LoginResponse.builder()
                .accessToken("mock-access-token-12345")
                .refreshToken("mock-refresh-token-67890")
                .role(Role.UNREGISTERED_PROFILE)
                .build();
    }

    public static LoginResponse createMockGuestLoginResponse() {
        return LoginResponse.builder()
                .accessToken("mock-access-token-12345")
                .role(Role.GUEST)
                .build();
    }

    public static RefreshTokenRequest createMockRefreshTokenRequest() {
        return new RefreshTokenRequest("mock-token-12345");
    }

    public static EmailRequest createMockEmailRequest() {
        return new EmailRequest(University.YONSEI, "genie@yonsei.ac.kr");
    }

    public static EmailResponse createMockEmailResponse() {
        return new EmailResponse("123456");
    }

    public static UserRevokeRequest createMockUserRevokeRequest() {
        return new UserRevokeRequest("mock-token-1234", RevokeReasonType.OTHER, "앱속도가 느려요");
    }
}
