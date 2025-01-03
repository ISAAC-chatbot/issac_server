package issac.issac_server.user.application.dto;

import issac.issac_server.user.domain.DegreeType;
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
    private DegreeType degree;

    @NotNull
    private String schoolEmail;

    @Builder
    public UserCreateRequest(String nickname, University university, String collegeName, String department, DegreeType degree, String schoolEmail) {
        this.nickname = nickname;
        this.university = university;
        this.collegeName = collegeName;
        this.department = department;
        this.degree = degree;
        this.schoolEmail = schoolEmail;
    }
}
