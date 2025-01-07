package issac.issac_server.user.application.dto;

import issac.issac_server.user.domain.DegreeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileUpdateRequest {

    @NotBlank
    private String nickname;

    @NotBlank
    private String collegeName;

    @NotBlank
    private String department;

    @NotNull
    private DegreeType degree;

    @NotBlank
    private String profilePhotoUrl;
}
