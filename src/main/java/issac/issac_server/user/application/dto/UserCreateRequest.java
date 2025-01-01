package issac.issac_server.user.application.dto;

import issac.issac_server.user.domain.DegreeType;
import issac.issac_server.user.domain.University;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
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

}
