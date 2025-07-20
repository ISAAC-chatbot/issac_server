package issac.issac_server.notification.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.Set;

@Getter
public class NotificationCreateRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    //    @NotNull
    private Set<String> deviceTokens;

    private boolean all;
}
