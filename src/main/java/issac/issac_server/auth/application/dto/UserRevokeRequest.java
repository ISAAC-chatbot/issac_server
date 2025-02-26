package issac.issac_server.auth.application.dto;

import issac.issac_server.user.domain.RevokeReasonType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRevokeRequest {

    @NotBlank
    private String token;

    @NotNull
    private RevokeReasonType reasonType;

    private String reasonDescription;
}
