package issac.issac_server.user.constant;

import issac.issac_server.auth.domain.OAuthProviderType;
import issac.issac_server.user.application.dto.SettingResponse;
import issac.issac_server.user.application.dto.UserResponse;
import issac.issac_server.user.domain.Role;
import issac.issac_server.user.domain.SettingType;

import static issac.issac_server.user.constant.ProfileFactory.createMockProfileResponse;

public class UserFactory {

    public static UserResponse createMockUserResponse() {
        return new UserResponse(
                1L,
                OAuthProviderType.KAKAO,
                "issac@kakao.com",
                Role.STUDENT,
                true,
                createMockProfileResponse()
        );
    }

    public static SettingResponse createMockSettingResponse() {
        return new SettingResponse(SettingType.NOTIFICATION, true);
    }

}
