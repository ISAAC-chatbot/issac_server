package issac.issac_server.user.application.dto;

import issac.issac_server.user.domain.EducationLevel;
import issac.issac_server.user.domain.University;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCreateRequest {

    @NotBlank
    private String nickname;

    @NotNull
    private University university;

    @NotBlank
    private String collegeName;

    @NotBlank
    private String department;

    @NotNull
    private EducationLevel educationLevel;

    @NotNull
    private Boolean marketingConsent;

    @Builder
    public UserCreateRequest(String nickname, University university, String collegeName, String department, EducationLevel educationLevel, Boolean marketingConsent) {
        this.nickname = nickname;
        this.university = university;
        this.collegeName = collegeName;
        this.department = department;
        this.educationLevel = educationLevel;
        this.marketingConsent = marketingConsent;
    }
}
