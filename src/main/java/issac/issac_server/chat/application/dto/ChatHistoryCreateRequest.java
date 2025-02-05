package issac.issac_server.chat.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ChatHistoryCreateRequest {

    @NotBlank
    private String question;

    @NotBlank
    private String answer;

    private String sourceURL;
}
