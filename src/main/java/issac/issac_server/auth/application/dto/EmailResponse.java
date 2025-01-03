package issac.issac_server.auth.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmailResponse {
    private String verificationCode;
}
