package issac.issac_server.user.constant;

import issac.issac_server.user.application.dto.ProfileCreateRequest;
import issac.issac_server.user.application.dto.ProfileResponse;
import issac.issac_server.user.application.dto.ProfileUpdateRequest;
import issac.issac_server.user.domain.EducationLevel;

import static issac.issac_server.user.domain.University.YONSEI;

public class ProfileFactory {

    public static ProfileCreateRequest createMockProfileCreateRequest() {
        return ProfileCreateRequest.builder()
                .nickname("지니")
                .university(YONSEI)
                .collegeName("공학")
                .department("인공지능학과")
                .educationLevel(EducationLevel.UNDERGRADUATE)
                .marketingConsent(true)
                .build();
    }

    public static ProfileResponse createMockProfileResponse() {
        return ProfileResponse.builder()
                .nickname("지니")
                .university(YONSEI)
                .educationLevel(EducationLevel.UNDERGRADUATE)
                .collegeName("공학")
                .department("인공지능학과")
                .schoolEmail("genie@yonsei.ac.kr")
                .profilePhotoUrl("https://issac-dev.s3.ap-northeast-2.amazonaws.com/images/profile/2025/01/08/5495e732-47d2-4edf-a216-a6885aecf438")
                .build();
    }

    public static ProfileUpdateRequest createMockProfileUpdateRequest() {
        return ProfileUpdateRequest.builder()
                .nickname("지니")
                .educationLevel(EducationLevel.UNDERGRADUATE)
                .collegeName("공학")
                .schoolEmail("genie@yonsei.ac.kr")
                .department("인공지능학과")
                .profilePhotoUrl("https://issac-dev.s3.ap-northeast-2.amazonaws.com/images/profile/2025/01/08/5495e732-47d2-4edf-a216-a6885aecf438")
                .build();
    }
}
