package issac.issac_server.auth.application.dto;

import issac.issac_server.user.domain.University;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class EmailRequest {
    private University university;
    private String email;
}
