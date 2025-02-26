package issac.issac_server.user.application.dto;

import issac.issac_server.user.domain.EducationLevel;
import issac.issac_server.user.domain.Profile;
import issac.issac_server.user.domain.University;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponse {

    private String nickname;

    private University university;

    private EducationLevel educationLevel;

    private String collegeName;

    private String department;

    private String schoolEmail;

    private String profilePhotoUrl;

    public static ProfileResponse from(Profile profile) {
        return new ProfileResponse(
                profile.getNickname(),
                profile.getUniversity(),
                profile.getEducationLevel(),
                profile.getCollegeName(),
                profile.getDepartment(),
                profile.getSchoolEmail(),
                profile.getProfilePhotoUrl()
        );
    }
}
