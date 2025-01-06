package issac.issac_server.user.constant;

import issac.issac_server.user.application.dto.ProfileResponse;
import issac.issac_server.user.domain.DegreeType;

import static issac.issac_server.user.domain.University.YONSEI;

public class ProfileFactory {

    public static ProfileResponse createMockProfileResponse() {
        return ProfileResponse.builder()
                .nickname("지니")
                .university(YONSEI)
                .collegeName("공학")
                .department("인공지능학과")
                .degree(DegreeType.BACHELOR)
                .schoolEmail("genie@yonsei.ac.kr")
                .build();
    }

}
