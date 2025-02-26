package issac.issac_server.user.application.dto;

import issac.issac_server.user.domain.EducationLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProfileUpdateRequest {

    private String nickname;

    private EducationLevel educationLevel;

    private String collegeName;

    private String department;

    private String profilePhotoUrl;
}
