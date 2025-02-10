package issac.issac_server.user.constant;

import issac.issac_server.auth.domain.OAuthProviderType;
import issac.issac_server.user.application.dto.SettingResponse;
import issac.issac_server.user.application.dto.UserCreateRequest;
import issac.issac_server.user.application.dto.UserResponse;
import issac.issac_server.user.domain.DegreeType;
import issac.issac_server.user.domain.Role;
import issac.issac_server.user.domain.SettingType;

import static issac.issac_server.user.domain.University.YONSEI;

public class UserFactory {

    public static UserCreateRequest createMockUserCreateRequest() {
        return UserCreateRequest.builder()
                .nickname("지니")
                .university(YONSEI)
                .collegeName("공학")
                .department("인공지능학과")
                .degree(DegreeType.BACHELOR)
                .schoolEmail("genie@yonsei.ac.kr")
                .marketingConsent(true)
                .build();
    }

    public static UserResponse createMockUserResponse() {
        return new UserResponse(
                1L,
                OAuthProviderType.KAKAO,
                "issac@kakao.com",
                Role.STUDENT,
                true
        );
    }

    public static SettingResponse createMockSettingResponse() {
        return new SettingResponse(SettingType.NOTIFICATION, true);
    }

}
