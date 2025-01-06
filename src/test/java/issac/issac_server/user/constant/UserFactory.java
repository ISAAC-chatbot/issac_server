package issac.issac_server.user.constant;

import issac.issac_server.user.application.dto.UserCreateRequest;
import issac.issac_server.user.domain.DegreeType;

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

}
